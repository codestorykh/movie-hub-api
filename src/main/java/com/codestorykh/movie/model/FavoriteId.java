package com.codestorykh.movie.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Embeddable
public class FavoriteId implements Serializable {

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
}
