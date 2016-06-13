package com.volomak.movieland.service.dto;

import com.volomak.movieland.entity.Country;
import org.springframework.stereotype.Service;

@Service
public class CountryListDtoConverter {
    public CountryListDto converter(Country country){
        CountryListDto countryListDto = new CountryListDto();
        countryListDto.setName(country.getName());
        return countryListDto;
    }
}
