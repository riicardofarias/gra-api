package com.movielist.api.dto;
import lombok.*;

import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieListDTO{
    List<MovieIntervalDTO> min;
    List<MovieIntervalDTO> max;
}
