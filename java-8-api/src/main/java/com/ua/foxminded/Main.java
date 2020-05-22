package com.ua.foxminded;

import java.io.IOException;

import com.ua.foxminded.controller.RaceFilesDataLoader;
import com.ua.foxminded.controller.RaceStatisticsBuilder;

public class Main {
    public static void main(String[] args) {
        RaceFilesDataLoader loader = new RaceFilesDataLoader();
        RaceStatisticsBuilder builder = new RaceStatisticsBuilder();
        try {
            builder.buildStatictics(loader.loadRacers());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("System error");
        }
    }
}
