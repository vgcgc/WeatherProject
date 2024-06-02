package com.zerobase.weatherproject.service;

import com.zerobase.weatherproject.WeatherProjectApplication;
import com.zerobase.weatherproject.domain.Diary;
import com.zerobase.weatherproject.domain.Weather;
import com.zerobase.weatherproject.repsitory.DiaryRepository;
import com.zerobase.weatherproject.repsitory.WeatherRepository;
import org.hibernate.query.sqm.ParsingException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@PropertySource("classpath:config.properties")
@Transactional
public class DiaryService {

    @Value("${WEATHER_API_KEY}")
    private String WEATHER_API_KEY;

    private final DiaryRepository diaryRepository;
    private final WeatherRepository weatherRepository;
    private static final Logger logger = LoggerFactory.getLogger(WeatherProjectApplication.class);

    public DiaryService(DiaryRepository diaryRepository, WeatherRepository weatherRepository) {
        this.diaryRepository = diaryRepository;
        this.weatherRepository = weatherRepository;
    }

    // POST / create / diary
    @Transactional
    public void createDiary(LocalDate date, String text){

        logger.info("start createDiary");

        Optional<Weather> weather = weatherRepository.findById(date);

        Diary newDiary = new Diary();
        newDiary.setDate(date);
        newDiary.setWeather(weather.get().getWeather_str());
        newDiary.setIcon(weather.get().getIcon());
        newDiary.setTemperature(weather.get().getTemperature());
        newDiary.setText(text);

        diaryRepository.save(newDiary);
    }

    // GET / read / diary
    @Transactional
    public List<Diary> readDiary(LocalDate date) {
        logger.info("start readDiary");
        return diaryRepository.findDiariesByDate(date);
    }

    // GET / read / diaries
    @Transactional
    public List<Diary> readDiaries(LocalDate startDate, LocalDate endDate){
        logger.info("start readDiaries");
        return diaryRepository.findAllByDateBetween(startDate, endDate);
    }

    // PUT / update / diary
    @Transactional
    public void updateDiary(LocalDate date, String text){
        logger.info("start updateDiary");
        Diary diary = diaryRepository.findDiariesByDate(date).get(0);
        diary.setText(text);
        diaryRepository.save(diary);
    }

    // DELETE / delete / diary
    @Transactional
    public void deleteDiary(LocalDate date){
        logger.info("start deleteDiary");
        diaryRepository.deleteAllByDate(date);
    }

    @Transactional
    @Scheduled(cron = "0 0 1 * * *")
    public void weatherSave(){
        logger.info("start weatherSave");
        weatherRepository.save(getWeather());
    }

    private Weather getWeather(){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity("parameters", headers);
        URI url = URI.create("https://api.openweathermap.org/data/2.5/weather?q=seoul&appid=" + WEATHER_API_KEY);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        JSONParser jsonParser = new JSONParser();
        Weather weather = new Weather();

        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody());
            JSONObject jsonWeather = (JSONObject) ((JSONArray) jsonObject.get("weather")).get(0);
            JSONObject jsonMain = (JSONObject) jsonObject.get("main");

            weather.setDate(LocalDate.now());
            weather.setWeather_str(jsonWeather.get("main").toString());
            weather.setIcon(jsonWeather.get("icon").toString());
            weather.setTemperature((Double) jsonMain.get("temp"));

            return weather;

        } catch (ParseException e) {
            throw new ParsingException("데이터 가져오기 실패");
        }
    }
}
