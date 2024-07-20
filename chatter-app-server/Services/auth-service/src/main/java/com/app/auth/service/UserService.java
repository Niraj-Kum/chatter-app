package com.app.auth.service;

import com.app.auth.dao.TokenRepository;
import com.app.auth.exception.ServiceException;
import com.app.auth.response.GeneralResponse;
import com.app.auth.response.RefreshTokenResponse;
import com.app.auth.response.UserAuthResponse;
import com.app.auth.config.JwtService;
import com.app.auth.dao.UserRepository;
import com.app.auth.entity.TokenEntity;
import com.app.auth.entity.UserEntity;
import com.app.auth.model.Role;
import com.app.auth.model.TokenType;
import com.app.auth.request.UserAuthRequest;
import com.app.auth.request.UserDetails;
import com.app.auth.utils.Constants;
import com.app.auth.utils.DBConstants;
import com.app.auth.utils.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public GeneralResponse<String> saveUserDetails(final UserDetails userDetails) throws ServiceException {
        GeneralResponse<String> response = new GeneralResponse<>();
        try {
            UserEntity user = ObjectUtil.convertObjectToEntity(new UserEntity(), userDetails);
            user.setRole(Role.ADMIN);
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
            Map<String, Object> res = userRepository.saveUser(
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getUsername(),
                    user.getRole().name(),
                    user.getMobile(),
                    user.getPassword(),
                    user.getPin(),
                    user.getBirthDate(),
                    user.getDisplayPictureUrl());
            Integer dbResponseCode = (Integer) res.get(DBConstants.RETURN_CODE);
            String dbResponseMessage = res.get(DBConstants.RETURN_MESSAGE).toString();
            response.setBody(dbResponseMessage);
            response.setStatus(dbResponseCode.intValue() == DBConstants.SUCCESS_CODE ? HttpStatus.CREATED : HttpStatus.CONFLICT);
            return response;
        } catch (DataAccessException e) {
            log.error("Error occurred when saving the user ", e);
            throw new ServiceException("Error: DB_500 Exception occurred when saving user Details");
        }
    }

    public UserAuthResponse authenticateUser(final UserAuthRequest userAuthRequest) throws ServiceException, AuthenticationException {
        try {
            authenticate(userAuthRequest);
            UserEntity user = userRepository.findByUsername(userAuthRequest.getUsername()).orElseThrow();
            String token = jwtService.generateToken(user);
            saveUserToken(user, token);
            return UserAuthResponse.builder().token(token).userId(user.getUserId()).username(user.getUsername()).firstName(user.getFirstName()).lastName(user.getLastName()).mobile(user.getMobile()).birthdate(user.getBirthDate()).build();
        } catch (DataAccessException e) {
            log.error("Exception Occurred While Authenticating the user -> {}, message -> {}", userAuthRequest.getUsername(), e.getMessage());
            throw new ServiceException("Error: DB_500 Exception occurred when saving user Details");
        }
    }

    public void authenticate(UserAuthRequest userAuthRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userAuthRequest.getUsername(), userAuthRequest.getPassword()));
    }

    private void saveUserToken(UserEntity user, String jwtToken) {
        TokenEntity token = TokenEntity.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(UserEntity user) {
        List<TokenEntity> validUserTokens = tokenRepository.findAllValidTokenByUser(user.getUserId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public GeneralResponse<RefreshTokenResponse> refreshToken(String authHeader) {
        final String refreshToken;
        final String username;
        if (authHeader == null || !authHeader.startsWith(Constants.BEARER)) {
            return ObjectUtil.getGeneralResponse(null, HttpStatus.UNAUTHORIZED, true, "Invalid token");
        }
        refreshToken = authHeader.substring(Constants.TOKEN_START_INDEX);
        username = jwtService.extractUsername(refreshToken);
        if (Objects.nonNull(username)) {
            var user = this.userRepository.findByUsername(username)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                String accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                return ObjectUtil.getGeneralResponse(RefreshTokenResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build(), HttpStatus.OK, false, null);
            }
        }
        return ObjectUtil.getGeneralResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, true, "Unable to fetch user Information from DB");
    }
}
