package com.app.auth.utils;

import com.app.auth.exception.ServiceException;
import com.app.auth.response.ErrorResponse;
import com.app.auth.response.GeneralResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class ObjectUtil {

    public static <T, M> M convertObjectToEntity(M entity, T request) throws ServiceException {
        Field[] requestFields = request.getClass().getDeclaredFields();
        for (Field field : requestFields) {
            StringBuilder fieldName = new StringBuilder();
            fieldName.append(field.getName().substring(0, 1).toUpperCase()).append(field.getName().substring(1));
            try {
                Object result = request.getClass().getDeclaredMethod("get" + fieldName.toString())
                        .invoke(request);
                Method method = entity.getClass().getDeclaredMethod("set" + fieldName.toString(), field.getType());
                method.invoke(entity, result);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                log.info("Exception Occurred while converting object: ", e);
                throw new ServiceException(e.getMessage());
            }
        }
        return entity;
    }

    public static <T> T getOptionalObject(Optional<T> object) {
        return object.orElse(null);
    }

    public static <T> GeneralResponse<T> getGeneralResponse(T body, HttpStatus status, boolean failure, String errorMessage) {
        GeneralResponse<T> response = new GeneralResponse<>();
        if(failure) {
            response.setError(ErrorResponse.builder().errorMessage(errorMessage).build());
        }
        response.setBody(body);
        response.setStatus(status);
        return response;
    }
}
