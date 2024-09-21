package com.codestorykh.movie.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;


@Getter
@Setter
@ToString
@Entity
@Table(name = "t_album_category")
public class AlbumCategory implements Serializable {

    /**
     * Composite primary key for the AlbumCategory entity.
     * Consists of 'albumId' and 'categoryId' fields.
     */
    @EmbeddedId
    private AlbumCategoryId albumCategoryId;

    @ManyToOne
    @MapsId("categoryId") // Maps the 'categoryId' part of AlbumCategoryId to the Category entity
    @JoinColumn(
            name = "category_id",
            foreignKey = @ForeignKey(name = "category_id_fk") // Custom foreign key constraint name
    )
    private Category category;

    @ManyToOne
    @MapsId("albumId") // Maps the 'albumId' part of AlbumCategoryId to the Album entity
    @JoinColumn(
            name = "album_id",
            foreignKey = @ForeignKey(name = "album_id_fk") // Custom foreign key constraint name
    )
    private Album album;

    @Column(
            name = "created_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE" // SQL column definition
    )
    private LocalDateTime createdAt;

    public AlbumCategory() {
    }

    public AlbumCategory(AlbumCategoryId albumCategoryId, Category category,
                         Album album, LocalDateTime createdAt) {
        this.albumCategoryId = albumCategoryId;
        this.category = category;
        this.album = album;
        this.createdAt = createdAt;
    }

    @Setter
    @Getter
    @Embeddable
    public static class AlbumCategoryId implements Serializable {

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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AlbumCategoryId that = (AlbumCategoryId) o;
            return Objects.equals(categoryId, that.categoryId) && Objects.equals(albumId, that.albumId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(categoryId, albumId);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumCategory that = (AlbumCategory) o;
        return Objects.equals(albumCategoryId, that.albumCategoryId) && Objects.equals(category, that.category) && Objects.equals(album, that.album) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(albumCategoryId, category, album, createdAt);
    }
}
