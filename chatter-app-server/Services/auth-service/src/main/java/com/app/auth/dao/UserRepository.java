package com.app.auth.dao;


import com.app.auth.entity.UserEntity;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUserId(int userId);
    Optional<UserEntity> findByUsername(String username);

    @Procedure(name  = "save_user")
    Map<String, Object> saveUser(
            @Param("pfirstName") final String firstName,
            @Param("plast_name") final String lastName,
            @Param("pemail") final String email,
            @Param("pusername") final String username,
            @Param("prole") final String role,
            @Param("pmobile") final String mobile,
            @Param("ppassword") final String password,
            @Param("ppin") final String pin,
            @Param("pbirthdate") final LocalDate birthdate,
            @Param("profile_pic_url") final String url) throws DataAccessException;
}
