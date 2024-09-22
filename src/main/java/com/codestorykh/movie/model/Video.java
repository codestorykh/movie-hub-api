package com.codestorykh.movie.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.codestorykh.movie.constant.TableConstant.*;

/**
 * Represents a video entity with episodes and URL.
 * Associated with a list of albums.
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = T_VIDEO)
public class Video implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Video episode number
    private int episode;

    // URL of the video resource
    private String url;

    @ToString.Exclude // Exclude 'albums' from the generated toString() method to prevent recursion
    @OneToMany(
            mappedBy = "video",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Album> albums = new ArrayList<>();
    public void addAlbum(Album album) {
        albums.add(album);
        album.setVideo(this);
    }

    public void removeAlbum(Album album) {
        albums.remove(album);
        album.setVideo(null);
    }
}
