package com.app.auth.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DBConstants {
    public static final String RETURN_CODE = "pReturnCode";
    public static final String RETURN_MESSAGE = "pReturnMessage";
    public static final Integer SUCCESS_CODE = 201;
    public static final Integer DUPLICATE_CONFLICT = 401;
}
