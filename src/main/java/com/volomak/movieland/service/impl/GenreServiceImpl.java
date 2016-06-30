package com.volomak.movieland.service.impl;

import com.volomak.movieland.dao.GenreDao;
import com.volomak.movieland.entity.Genre;
import com.volomak.movieland.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreDao genreDao;

    @Override
    public Genre getById(Long id) {
        return genreDao.getById(id);
    }

    @Override
    public List<Genre> getByMovieId(Long id) {
        return genreDao.getByMovieId(id);
    }

    @Override
    public List<Genre> getIdsByMovieId(Long id) {
        return genreDao.getIdsByMovieId(id);
    }

    @Override
    public List<Genre> getGenres() {
        return genreDao.getGenres();
    }
}
