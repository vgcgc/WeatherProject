package com.zerobase.weatherproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WeatherProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherProjectApplication.class, args);
    }

}
