package com.movielist.api;

import com.movielist.api.dto.MovieIntervalDTO;
import com.movielist.api.dto.MovieListDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class MovieControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    void movieListGetSuccessful() {
        var min = new MovieIntervalDTO("Bo Derek", 1, 1990, 1991);
        var max = new MovieIntervalDTO("Allan Carr", 4, 1980, 1984);
        var fake = new MovieListDTO(List.of(min), List.of(max));

        var url  = String.format("http://localhost:%s", port);
        var response = restTemplate.exchange(url, HttpMethod.GET,
            new HttpEntity<>(new HttpHeaders()), new ParameterizedTypeReference<MovieListDTO>() {}
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isEqualTo(fake);
    }
}
