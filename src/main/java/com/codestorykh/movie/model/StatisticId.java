package com.codestorykh.movie.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Embeddable
public class StatisticId implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "album_id")
    private Long albumId;

    public StatisticId() {

    }

    public StatisticId(Long userId, Long albumId) {
        this.userId = userId;
        this.albumId = albumId;
    }

    @Override
    public String toString() {
        return "StatisticId{" +
                "userId=" + userId +
                ", albumId=" + albumId +
                '}';
    }
}
