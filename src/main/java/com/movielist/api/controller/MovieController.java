package com.movielist.api.controller;

import com.movielist.api.service.MovieService;
import com.movielist.api.dto.MovieListDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @Operation(summary = "Filmes vencedores do Golden Raspberry Awards")
    @GetMapping
    public ResponseEntity<MovieListDTO> getAll() {
        var movies = movieService.getMinMax();
        return ResponseEntity.ok(movies);
    }
}
