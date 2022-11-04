package com.movielist.api.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class MovieProps {
    @Value("${movie.data.path}")
    private String path;
}
