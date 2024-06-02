package com.zerobase.weatherproject.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    WEATHER_API_ERROR(HttpStatus.NOT_FOUND, "Weather Api 에러가 발생했습니다."),
    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "DB 에러가 발생했습니다.");

    private HttpStatus status;
    private String message;

}
