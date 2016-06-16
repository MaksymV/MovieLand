package com.volomak.movieland.service.impl;

import com.volomak.movieland.dao.CountryDao;
import com.volomak.movieland.entity.Country;
import com.volomak.movieland.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryDao countryDao;

    @Override
    public List<Country> getByMovieId(Long id) {
        return countryDao.getByMovieId(id);
    }
}
