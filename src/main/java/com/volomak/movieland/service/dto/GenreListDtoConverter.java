package com.volomak.movieland.service.dto;

import com.volomak.movieland.entity.Genre;
import org.springframework.stereotype.Service;

@Service
public class GenreListDtoConverter {

    public GenreListDto convert(Genre genre){
        GenreListDto genreListDto = new GenreListDto();
        genreListDto.setName(genre.getName());
        return genreListDto;
    }
}
