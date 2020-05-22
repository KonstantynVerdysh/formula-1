package com.ua.foxminded.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ua.foxminded.model.Racer;

/**
 * @author Konstantyn Verdysh
 *
 */
class RaceStatisticsBuilderTest {
    private RaceStatisticsBuilder builder = new RaceStatisticsBuilder();
    private ByteArrayOutputStream actual = new ByteArrayOutputStream();
    private ByteArrayOutputStream expected = new ByteArrayOutputStream();
    
    @BeforeEach
    public void before() {
        System.setOut(new PrintStream(expected));
    }
    /**
     * Test method for {@link com.ua.foxminded.controller.RaceStatisticsBuilder#buildStatictics(java.util.List)}.
     */
    @Test
    public void buildStatictics_printStatisticsWhenInputIsListOfRacer() {
        Racer r1 = new Racer("Daniel Ricciardo", "RED BULL RACING TAG HEUER");
        Racer r2 = new Racer("Sebastian Vettel", "FERRARI");
        Racer r3 = new Racer("Lewis Hamilton", "MERCEDES");
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
        r1.setStart(LocalDateTime.parse("2018-05-24_12:14:12.054", formatter));
        r2.setStart(LocalDateTime.parse("2018-05-24_12:02:50.917", formatter));
        r3.setStart(LocalDateTime.parse("2018-05-24_12:18:20.125", formatter));
        
        r1.setEnd(LocalDateTime.parse("2018-05-24_12:15:24.067", formatter));
        r2.setEnd(LocalDateTime.parse("2018-05-24_12:04:03.332", formatter));
        r3.setEnd(LocalDateTime.parse("2018-05-24_12:19:32.585", formatter));
        
        r1.setTime(LocalDateTime.parse("2018-05-24_00:01:12.013", formatter));
        r2.setTime(LocalDateTime.parse("2018-05-24_00:01:12.415", formatter));
        r3.setTime(LocalDateTime.parse("2018-05-24_00:01:12.460", formatter));
        
        List<Racer> racers = new ArrayList<>();
        racers.add(r1);
        racers.add(r2);
        racers.add(r3);
        
        builder.buildStatictics(racers);
        System.setOut(new PrintStream(actual));
        String[] output = {" 1. Daniel Ricciardo | RED BULL RACING TAG HEUER | 1:12.013",
                           " 2. Sebastian Vettel | FERRARI                   | 1:12.415",
                           " 3. Lewis Hamilton   | MERCEDES                  | 1:12.460"};
        Arrays.stream(output).forEach(System.out::println);
        
        assertEquals(expected.toString(), actual.toString());
    }
    
    @AfterEach
    public void after() {
        System.setOut(null);
    }
}
