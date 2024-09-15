package com.codestorykh.movie.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Table(name = "t_favorite")
@Entity
public class Favorite implements Serializable {

    @EmbeddedId
    private FavoriteId favoriteId;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id",
            foreignKey = @ForeignKey(name = "user_id_fk")
    )
    private User user;

    @ManyToOne
    @MapsId("albumId")
    @JoinColumn(name = "album_id",
            foreignKey = @ForeignKey(name = "album_id_fk")
    )
    private Album album;

    @ManyToOne
    @MapsId("videoId")
    @JoinColumn(name = "video_id",
            foreignKey = @ForeignKey(name = "video_id_fk")
    )
    private Video video;

}
