package com.zerobase.weatherproject.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;


@SpringBootTest
class DiaryServiceTest {

    @Autowired
    DiaryService diaryService;

    @Test
    // given
    void createDiaryTest(){
        //given
        LocalDate date = LocalDate.now();
        // when
        diaryService.createDiary(date, "가나다");
        // then

    }

    @Test
    void readDiaryTest() {
        // given
        LocalDate date = LocalDate.now();
        // when
        diaryService.readDiary(date);
        // then
    }

    @Test
    void readDiariesTest() {
        // given
        LocalDate startDate = LocalDate.of(2024, 6, 1);
        LocalDate endDate = LocalDate.now();
        // when
        diaryService.readDiaries(startDate, endDate);
        // then
    }

    @Test
    void updateDiaryTest() {
        // given
        LocalDate date = LocalDate.now();
        String text = "this is a new text";
        // when
        diaryService.updateDiary(date, text);
        // then
    }

    @Test
    void deleteDiaryTest() {
        // given
        LocalDate date = LocalDate.now();
        // when
        diaryService.deleteDiary(date);
        // then
    }

    @Test
    void getWeatherTest() {
        // given
        // when
        // then
        diaryService.weatherSave();
    }

}