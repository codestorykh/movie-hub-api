package com.codestorykh.movie.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

import static com.codestorykh.movie.constant.TableConstant.T_MOVIE;

/**
 * Represents a Movie entity with attributes such as code, description, and order.
 * This entity is mapped to the m_movie table in the database.
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = T_MOVIE)
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String description;

    // Custom order for sorting movies
    @Column(name = "m_order")
    private int mOrder;

    public Movie() {
    }

    public Movie(String code, String description, int mOrder) {
        this.code = code;
        this.description = description;
        this.mOrder = mOrder;
    }
}
