package com.codestorykh.movie.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

import static com.codestorykh.movie.constant.TableConstant.T_FAVORITE;

@Getter
@Setter
@ToString
@Entity
@Table(name = T_FAVORITE)
public class Favorite implements Serializable {

    @EmbeddedId
    private FavoriteId favoriteId;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(
            name = "user_id",
            foreignKey = @ForeignKey(name = "user_id_fk")
    )
    private User user;

    @ManyToOne
    @MapsId("albumId")
    @JoinColumn(
            name = "album_id",
            foreignKey = @ForeignKey(name = "album_id_fk")
    )
    private Album album;

    @ManyToOne
    @MapsId("videoId")
    @JoinColumn(
            name = "video_id",
            foreignKey = @ForeignKey(name = "video_id_fk")
    )
    private Video video;

    public Favorite() {
    }

    public Favorite(FavoriteId favoriteId, User user, Album album, Video video) {
        this.favoriteId = favoriteId;
        this.user = user;
        this.album = album;
        this.video = video;
    }


    @Setter
    @Getter
    @Embeddable
    public static class FavoriteId implements Serializable {

        @Column(name = "user_id")
        private Long userId;

        @Column(name = "album_id")
        private Long albumId;

        @Column(name = "video_id")
        private Long videoId;

        public FavoriteId() {
        }

        public FavoriteId(Long userId, Long albumId, Long videoId) {
            this.userId = userId;
            this.albumId = albumId;
            this.videoId = videoId;
        }

        @Override
        public String toString() {
            return "FavoriteId{" +
                    "userId=" + userId +
                    ", albumId=" + albumId +
                    ", videoId=" + videoId +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FavoriteId that = (FavoriteId) o;
            return Objects.equals(userId, that.userId) && Objects.equals(albumId, that.albumId) && Objects.equals(videoId, that.videoId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(userId, albumId, videoId);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favorite favorite = (Favorite) o;
        return Objects.equals(favoriteId, favorite.favoriteId) && Objects.equals(user, favorite.user) && Objects.equals(album, favorite.album) && Objects.equals(video, favorite.video);
    }

    @Override
    public int hashCode() {
        return Objects.hash(favoriteId, user, album, video);
    }
}
