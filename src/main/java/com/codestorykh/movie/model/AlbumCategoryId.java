package com.codestorykh.movie.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Embeddable
public class AlbumCategoryId implements Serializable {

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "album_id")
    private Long albumId;

    public AlbumCategoryId() {
    }

    public AlbumCategoryId(Long categoryId, Long albumId) {
        this.categoryId = categoryId;
        this.albumId = albumId;
    }

    @Override
    public String toString() {
        return "AlbumCategoryId{" +
                "categoryId=" + categoryId +
                ", albumId=" + albumId +
                '}';
    }
}