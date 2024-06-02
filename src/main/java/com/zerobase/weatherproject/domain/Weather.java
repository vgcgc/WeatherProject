package com.zerobase.weatherproject.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Weather {
    @Id
    private LocalDate date;
    private String weather_str;
    private String icon;
    private double temperature;
}
