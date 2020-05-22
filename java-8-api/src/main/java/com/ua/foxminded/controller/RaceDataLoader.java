package com.ua.foxminded.controller;

import java.io.IOException;
import java.util.List;

import com.ua.foxminded.model.Racer;

public interface RaceDataLoader {
    List<Racer> loadRacers() throws IOException;
}
