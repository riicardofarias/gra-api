package com.movielist.api.service;

import com.movielist.api.dto.MovieSaveDTO;
import com.movielist.api.dto.MovieIntervalDTO;
import com.movielist.api.dto.MovieListDTO;
import com.movielist.api.entity.model.Movie;
import com.movielist.api.entity.repository.MovieRepository;
import com.movielist.api.mapper.MovieMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class MovieService {
    private final MovieRepository movieRepository;

    /**
     * Salva um novo filme no banco de dados
     * @param dto Objeto de transferência de dados
     * @return {@link Movie} filme salvo
     */
    public Movie create(MovieSaveDTO dto) {
        var movie = MovieMapper.INSTANCE.toMovie(dto);
        return movieRepository.save(movie);
    }

    /**
     * Retorna a lista de filmes com maior e menor intervalo entre dois prêmios consecutivos
     * @return {@link MovieListDTO} lista de filmes
     */
    public MovieListDTO getMinMax() {
        var intervals = getIntervals();

        // Menor intervalo
        var minInterval = intervals.stream()
            .min(Comparator.comparing(MovieIntervalDTO::getInterval))
        .map(MovieIntervalDTO::getInterval).orElse(0);

        // Maior intervalo
        var maxInterval = intervals.stream()
            .max(Comparator.comparing(MovieIntervalDTO::getInterval))
        .map(MovieIntervalDTO::getInterval).orElse(0);

        // Filmes com menor intervalo
        var min = intervals.stream()
            .filter(e -> e.getInterval().equals(minInterval)
        ).collect(Collectors.toList());

        // Filmes com maior intervalo
        var max = intervals.stream()
            .filter(e -> e.getInterval().equals(maxInterval)
        ).collect(Collectors.toList());

        return new MovieListDTO(min, max);
    }

    /**
     * Obtém os intervalos entre os anos de produção dos filmes vencedores
     * @return Lista de intervalos
     */
    private List<MovieIntervalDTO> getIntervals() {
        // Obtém a lista de filmes vencedores ordenados por ano de produção
        var movies = movieRepository.findAllByWinnerIsTrue(
            Sort.by(Sort.Direction.ASC,"year", "producers")
        );

        // Separa o nome dos produtores
        var filtered = movies.stream()
            .flatMap(e -> Arrays.stream(e.getProducers().split(",|and")))
            .filter(e -> !e.isBlank())
            .map(String::trim)
        .collect(Collectors.toSet());

        // Lista de intervalos
        var intervals = new ArrayList<MovieIntervalDTO>();

        for(var producer : filtered) {
            var producerMovies = movies.stream()
                .filter(e -> e.getProducers().contains(producer))
            .collect(Collectors.toList());

            // Itera a lista de filmes
            for(var i = 0; i < producerMovies.size(); i++) {
                // Verifica se possui um próximo registro para iterar
                if(i + 1 == producerMovies.size()) {
                    continue;
                }

                var year = producerMovies.get(i).getYear();
                var nextYear = producerMovies.get(i + 1).getYear();
                var interval = nextYear - year;

                intervals.add(new MovieIntervalDTO(
                    producer, interval, year, nextYear
                ));
            }
        }

        return intervals;
    }
}
