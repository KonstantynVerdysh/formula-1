package com.ua.foxminded.controller;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.ua.foxminded.model.Racer;

public class RaceStatisticsBuilder {
    private static final int LINE_INDEX = 14;
    private static final String LINE = "-";
    private static final String JOINER = " | ";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("m:ss.SSS");
    
    public void buildStatictics(List<Racer> racers) {
        int maxNameSize = getMaxNameSize(racers).orElse(0);
        int maxTeamSize = getMaxTeamSize(racers).orElse(0);
        racers.sort(Comparator.comparing(Racer::getTime));
        racers.stream()
                .map(v -> buildRow(v, racers.indexOf(v), maxNameSize, maxTeamSize))
                .forEach(System.out::println);
    }
    
    private String buildRow(Racer r, int index, int maxNameSize, int maxTeamSize) {
        StringBuilder result = new StringBuilder();
        result.append(String.format("%2d. %-"+maxNameSize+"s%s%-"+maxTeamSize+"s%s" + r.getTime().format(DATE_TIME_FORMATTER), 
                index + 1, r.getName(), JOINER, r.getTeam(), JOINER));
        if (index == LINE_INDEX) {
            result.append("\n" + printUnderLine(result.length()));
        }
        return result.toString();
    }
    
    private String printUnderLine(int size) {
        StringBuilder result = new StringBuilder(size);
        for (int count = 0; count < size; count++)
            result.append(LINE);
        return result.toString();
    }
    
    private Optional<Integer> getMaxNameSize(List<Racer> racers) {
        return racers.stream()
                .map(v -> v.getName().length())
                .max(Integer::compare);
    }
    
    private Optional<Integer> getMaxTeamSize(List<Racer> racers) {
        return racers.stream()
                .map(v -> v.getTeam().length())
                .max(Integer::compare);
    }
}
