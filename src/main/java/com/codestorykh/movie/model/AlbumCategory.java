package com.codestorykh.movie.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Table(name = "t_album_category")
@Entity
public class AlbumCategory implements Serializable {

    @EmbeddedId
    private AlbumCategoryId albumCategoryId;

    @ManyToOne
    @MapsId("categoryId")
    @JoinColumn(name = "category_id",
            foreignKey = @ForeignKey(name = "category_id_fk")
    )
    private Category category;

    @ManyToOne
    @MapsId("albumId")
    @JoinColumn(name = "album_id",
            foreignKey = @ForeignKey(name = "album_id_fk")
    )
    private Album album;

    @Column(name = "created_at", nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime createdAt;

    public AlbumCategory(){}

    public AlbumCategory(AlbumCategoryId albumCategoryId, Category category,
                         Album album, LocalDateTime createdAt) {
        this.albumCategoryId = albumCategoryId;
        this.category = category;
        this.album = album;
        this.createdAt = createdAt;
    }
}
