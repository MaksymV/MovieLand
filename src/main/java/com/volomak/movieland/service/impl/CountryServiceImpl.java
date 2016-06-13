package com.volomak.movieland.service.impl;

import com.volomak.movieland.dao.CountryDao;
import com.volomak.movieland.entity.Country;
import com.volomak.movieland.service.CountryService;
import com.volomak.movieland.service.dto.CountryListDto;
import com.volomak.movieland.service.dto.CountryListDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryDao countryDao;

    @Autowired
    private CountryListDtoConverter countryListDtoConverter;

    @Override
    public Country getById(int id) {
        return null;
    }

    @Override
    public List<CountryListDto> getByMovieId(int id) {
        List<CountryListDto> countryListDtos = new ArrayList();
        for (Country country : countryDao.getByMovieId(id)){
            countryListDtos.add(countryListDtoConverter.converter(country));
        }
        return countryListDtos;
    }

    @Override
    public void add(Country country) {

    }
}
