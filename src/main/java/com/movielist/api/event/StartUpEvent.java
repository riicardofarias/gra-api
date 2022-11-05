package com.movielist.api.event;

import com.movielist.api.dto.MovieSaveDTO;
import com.movielist.api.service.MovieService;
import com.movielist.api.util.FileReaderUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class StartUpEvent implements ApplicationListener<ContextRefreshedEvent> {
    private final MovieService movieService;

    @Value("classpath:data/movielist.csv")
    private Resource resource;

    @SneakyThrows
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        var lines = FileReaderUtil.getFile(resource.getFile());

        for(String[] line: lines) {
            var year = Integer.parseInt(line[0]);
            var title = line[1];
            var studios = line[2];
            var producers = line[3];
            var winner = line[4].equalsIgnoreCase("yes");

            var movie = new MovieSaveDTO(
                year, title, studios, producers, winner
            );

            movieService.create(movie);
        }
    }
}
