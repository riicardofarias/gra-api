package com.movielist.api.dto;

import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieSaveDTO{
    Integer year;
    String title;
    String studios;
    String producers;
    Boolean winner;
}
