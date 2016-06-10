package com.volomak.movieland.dao;

import com.volomak.movieland.entity.Country;

public interface CoutryDao {
    Country getById(int id);

    void add(Country country);
}
