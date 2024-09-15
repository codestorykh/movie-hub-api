package com.codestorykh.movie.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Table(name = "t_video")
@Entity
public class Video implements Serializable {

    @Id
    @SequenceGenerator(
            name = "t_video_sequence",
            sequenceName = "t_video_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "t_video_sequence"
    )
    private Long id;

    private int episode;
    private String url;

    @ToString.Exclude
    @OneToMany(
            mappedBy = "video",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true,
            fetch = FetchType.LAZY // lazy loading when we get student we don't get books
    )
    private List<Album> albums = new ArrayList<>();
}
