package com.ua.foxminded.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ua.foxminded.model.Racer;

public class RaceFilesDataLoader implements RaceDataLoader {
    private static final String UNDERSCORE = "_";
    private static final int KEY_INDEX = 3;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
    private static final String START_FILE = "start.log";
    private static final String FINISH_FILE = "end.log";
    private static final String ABBREVIATIONS_FILE = "abbreviations.txt";
    
    public List<Racer> loadRacers() throws IOException {
        String[] startData = readFile(getFileFromResourses(START_FILE));
        String[] endData = readFile(getFileFromResourses(FINISH_FILE));
        String[] abbreviationsData = readFile(getFileFromResourses(ABBREVIATIONS_FILE));
        Map<String, Racer> racers = createRacers(abbreviationsData);
        processRacers(racers, startData, endData);
        return initialRacersList(racers);
    }
    
    private void checkFilesForDataExistense(File file) throws IOException {
        if (file.length() == 0)
            throw new IOException("File \"" + file.getName() + "\" is empty");
    }
    
    private File getFileFromResourses(String fileName) throws IOException {
        if (getClass().getClassLoader().getResource(fileName) == null)
            throw new IOException("File \"" + fileName + "\" not found");
        return new File(getClass().getClassLoader().getResource(fileName).getFile());
    }
    
    private String[] readFile(File file) throws IOException {
        checkFilesForDataExistense(file);
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
                Stream<String> stream = reader.lines()) {
            return stream.toArray(String[]::new);
        } catch (IOException e) {
            throw new IOException("Exception while reading \"" + file.getName() + "\"");
        }
    }
    
    private Map<String, Racer> createRacers(String[] abbreviationsData) {
        return Arrays.asList(abbreviationsData).stream()
                .map(this::divideDecription)
                .collect(Collectors.toMap(k -> k[0], v -> new Racer(v[1], v[2])));
    }
    
    private void processRacers(Map<String, Racer> racers, String[] startData, String[] endData) {
        processStart(racers, startData);
        processEnd(racers, endData);
        processTime(racers);
    }
    
    private void processStart(Map<String, Racer> racers, String[] startData) {
        for (int count = 0; count < startData.length; count++) {
            String abbreviation = divideAbbreviation(startData[count]);
            LocalDateTime startTime = divideTime(startData[count]);
            racers.get(abbreviation).setStart(startTime);
        }
    }
    
    private void processEnd(Map<String, Racer> racers, String[] endData) {
        for (int count = 0; count < endData.length; count++) {
            String abbreviation = divideAbbreviation(endData[count]);
            LocalDateTime endTime = divideTime(endData[count]);
            racers.get(abbreviation).setEnd(endTime);
        }
    }
    
    private void processTime(Map<String, Racer> racers) {
        for (Map.Entry<String, Racer> entry : racers.entrySet()) {
            LocalDateTime startTime = entry.getValue().getStart();
            LocalDateTime endTime = entry.getValue().getEnd();
            LocalDateTime result = calculateTime(startTime, endTime);
            entry.getValue().setTime(result);
        }
    }
    
    private List<Racer> initialRacersList(Map<String, Racer> racers) {
        return racers.entrySet().stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
    
    private String[] divideDecription(String str) {
        return str.split(UNDERSCORE);
    }
    
    private LocalDateTime calculateTime(LocalDateTime start, LocalDateTime end) {
        return end.minusHours(start.getHour()).minusMinutes(start.getMinute()).minusSeconds(start.getSecond()).minusNanos(start.getNano());
    }
    
    private String divideAbbreviation(String str) {
        return str.substring(0, KEY_INDEX);
    }
    
    private LocalDateTime divideTime(String str) {
        String time = str.substring(3, str.length());
        return LocalDateTime.parse(time, DATE_TIME_FORMATTER);
    }
}
