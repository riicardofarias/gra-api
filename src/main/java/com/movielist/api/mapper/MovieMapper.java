package com.movielist.api.mapper;

import com.movielist.api.dto.MovieSaveDTO;
import com.movielist.api.entity.model.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MovieMapper {
    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    Movie toMovie(MovieSaveDTO movie);
}
