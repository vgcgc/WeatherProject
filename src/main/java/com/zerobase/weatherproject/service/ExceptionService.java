package com.zerobase.weatherproject.service;

import com.zerobase.weatherproject.domain.ErrorCode;
import com.zerobase.weatherproject.domain.MyException;
import org.springframework.stereotype.Service;

@Service
public class ExceptionService {

    public void apiError(){
        throw new MyException(ErrorCode.WEATHER_API_ERROR);
    }

    public void dbError(){
        throw new MyException(ErrorCode.DATABASE_ERROR);
    }
}
