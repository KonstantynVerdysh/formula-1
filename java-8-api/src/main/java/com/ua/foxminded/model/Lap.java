package com.ua.foxminded.model;

import java.time.LocalDateTime;

public class Lap {
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime time;
    
    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}