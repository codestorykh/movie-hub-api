package com.codestorykh.movie.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Table(name = "m_movie")
@Entity
public class Movie implements Serializable {

    @Id
    @SequenceGenerator(
            name = "m_movie_sequence",
            sequenceName = "m_movie_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "m_movie_sequence"
    )
    private Long id;

    private String code;
    private String description;
    private int mOrder;
}
