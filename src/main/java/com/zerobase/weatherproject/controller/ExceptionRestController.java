package com.zerobase.weatherproject.controller;

import com.zerobase.weatherproject.service.ExceptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
@RequiredArgsConstructor
public class ExceptionRestController {

    private final ExceptionService exceptionService;

    @GetMapping("throw-my-exception/api-error")
    public void throwMyExceptionApiError() {
        exceptionService.apiError();
    }

    @GetMapping("throw-my-exception/db-error")
    public void throwMyExceptionDbError(){
        exceptionService.dbError();
    }
}
