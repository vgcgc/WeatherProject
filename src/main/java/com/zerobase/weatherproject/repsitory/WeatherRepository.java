package com.zerobase.weatherproject.repsitory;

import com.zerobase.weatherproject.domain.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface WeatherRepository extends JpaRepository<Weather, LocalDate> {

}
