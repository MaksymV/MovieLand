package com.volomak.movieland.service.impl;

import com.volomak.movieland.dao.GenreDao;
import com.volomak.movieland.entity.Genre;
import com.volomak.movieland.service.GenreService;
import com.volomak.movieland.service.dto.GenreListDto;
import com.volomak.movieland.service.dto.GenreListDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreDao genreDao;

    @Autowired
    private GenreListDtoConverter genreListDtoConverter;

    @Override
    public Genre getById(Long id) {
        return genreDao.getById(id);
    }

    @Override
    public List<GenreListDto> getByMovieId(Long id) {
        List<GenreListDto> genreListDtos = new ArrayList<>();
        for (Genre genre : genreDao.getByMovieId(id)){
            genreListDtos.add(genreListDtoConverter.convert(genre));
        }
        return genreListDtos;
    }
}
