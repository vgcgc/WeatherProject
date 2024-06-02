package com.zerobase.weatherproject.controller;

import com.zerobase.weatherproject.domain.Diary;
import com.zerobase.weatherproject.service.DiaryService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    // POST / create / diary
    @PostMapping("/create/diary")
    public void createDiary(@RequestParam("date") LocalDate date,
                            @RequestBody String text) {
        diaryService.createDiary(date, text);
    }

    // GET / read / diary
    @GetMapping("/read/diary")
    public List<Diary> readDiary(@RequestParam("date") LocalDate date){
        return diaryService.readDiary(date);
    }

    // GET / read / diaries
    @GetMapping("/read/diaries")
    public List<Diary> readDiares(@RequestParam("startDate") LocalDate startDate,
                                  @RequestParam("endDate") LocalDate endDate){
        return diaryService.readDiaries(startDate, endDate);
    }

    // PUT / update / diary
    @PutMapping("/update/diary")
    public void updateDiary(@RequestParam("date") LocalDate date,
                            @RequestBody String text){
        diaryService.updateDiary(date, text);
    }

    // DELETE / delete / diary
    @DeleteMapping("delete/diary")
    public void deleteDiary(@RequestParam("date") LocalDate date){
        diaryService.deleteDiary(date);
    }
}
