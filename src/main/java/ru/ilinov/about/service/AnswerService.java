package ru.ilinov.about.service;

import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    //TODO Обработка неккоректного значения
    public int checkAndConvertTimeToAbsolute(String time) {
        String[] timeArray;
        switch (time.split(":").length) {
            case 3:
                timeArray = time.split(":");
                return Integer.parseInt(timeArray[0]) * 3600 + Integer.parseInt(timeArray[1]) * 60 + Integer.parseInt(timeArray[0]);
            case 2:
                timeArray = time.split(":");
                return Integer.parseInt(timeArray[0]) * 60 + Integer.parseInt(timeArray[1]);
        }
        return Integer.parseInt(time);
    }

}
