package com.movielist.api.entity.model;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private Integer year;

    @Column
    private String studios;

    @Column
    private String producers;

    @Column
    private Boolean winner;
}
