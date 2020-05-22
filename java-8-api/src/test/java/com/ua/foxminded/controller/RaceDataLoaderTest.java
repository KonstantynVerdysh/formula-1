package com.ua.foxminded.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.ua.foxminded.model.Racer;

/**
 * @author Konstantyn Verdysh
 *
 */
class RaceDataLoaderTest {
    private RaceFilesDataLoader loader = new RaceFilesDataLoader();
    /**
     * Test method for {@link com.ua.foxminded.controller.RaceFilesDataLoader#loadRacers()}.
     */
    @Test
    public void loadReturnsExpectedRacers() {
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
        
        List<Racer> actual = null;
        try {
            actual = loader.loadRacers();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        assertTrue(actual.size() == 19);
        
        Racer r1Actual = null;
        Racer r2Actual = null;
        Racer r3Actual = null;
        for (Racer r : actual) {
            switch (r.getName()) {
                case "Daniel Ricciardo": r1Actual = r;
                break;
                case "Sebastian Vettel": r2Actual = r;
                break;
                case "Lewis Hamilton": r3Actual = r;
                break;
            }
        }
        assertEquals(r1.getName(), r1Actual.getName());
        assertEquals(r1.getTeam(), r1Actual.getTeam());
        assertEquals(r1.getTime(), r1Actual.getTime());
        
        assertEquals(r2.getName(), r2Actual.getName());
        assertEquals(r2.getTeam(), r2Actual.getTeam());
        assertEquals(r2.getTime(), r2Actual.getTime());
        
        assertEquals(r3.getName(), r3Actual.getName());
        assertEquals(r3.getTeam(), r3Actual.getTeam());
        assertEquals(r3.getTime(), r3Actual.getTime());
    }
}
