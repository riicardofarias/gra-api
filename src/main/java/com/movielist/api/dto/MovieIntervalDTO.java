package com.movielist.api.dto;

import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieIntervalDTO {
    String producer;
    Integer interval;
    Integer previousWin;
    Integer followingWin;
}
