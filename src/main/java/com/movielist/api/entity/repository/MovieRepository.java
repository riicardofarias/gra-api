package com.movielist.api.entity.repository;

import com.movielist.api.entity.model.Movie;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findAllByWinnerIsTrue(Sort sort);
}
