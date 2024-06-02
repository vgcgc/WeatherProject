package com.zerobase.weatherproject.repsitory;

import com.zerobase.weatherproject.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, LocalDate> {

    List<Diary> findDiariesByDate(LocalDate date);

    List<Diary> findAllByDateBetween(LocalDate startDate, LocalDate endDate);

    void deleteAllByDate(LocalDate date);
}
