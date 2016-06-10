package com.volomak.movieland.service.impl;

import com.volomak.movieland.dao.GenreDao;
import com.volomak.movieland.entity.Genre;
import com.volomak.movieland.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreDao genreDao;

    @Override
    public Genre getById(int id) {
        return genreDao.getById(id);
    }

    @Override
    public void add(Genre genre) {
        genreDao.add(genre);
    }
}
