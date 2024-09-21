package com.codestorykh.movie.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Represents a Movie entity with attributes such as code, description, and order.
 * This entity is mapped to the m_movie table in the database.
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "m_movie")
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Unique code for the movie
    private String code;

    // Description of the movie
    private String description;

    // Custom order for sorting movies
    @Column(name = "m_order")
    private int mOrder;

    /**
     * Default constructor required by JPA.
     */
    public Movie() {
    }

    /**
     * Parameterized constructor for creating a Movie entity.
     *
     * @param code        Unique code for the movie.
     * @param description Description of the movie.
     * @param mOrder      Custom order for sorting.
     */
    public Movie(String code, String description, int mOrder) {
        this.code = code;
        this.description = description;
        this.mOrder = mOrder;
    }
}
