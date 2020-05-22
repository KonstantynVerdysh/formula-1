package com.ua.foxminded.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Racer {   
    private String name;
    private String team;
    private List<Lap> laps;
    private static final int FIRST_LAP = 0;

    public Racer(String name, String team) {
        this.name = name;
        this.team = team;
        laps = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }
    public String getTeam() {
        return team;
    }
    public LocalDateTime getStart() {
        return laps.get(FIRST_LAP).getStart();
    }
    public LocalDateTime getEnd() {
        return laps.get(FIRST_LAP).getEnd();
    }
    public LocalDateTime getTime() {
        return laps.get(FIRST_LAP).getTime();
    }
    public void setStart(LocalDateTime start) {
        laps.add(new Lap());
        laps.get(FIRST_LAP).setStart(start);
    }
    public void setEnd(LocalDateTime end) {
        laps.get(FIRST_LAP).setEnd(end);
    }
    public void setTime(LocalDateTime time) {
        laps.get(FIRST_LAP).setTime(time);
    }
}
