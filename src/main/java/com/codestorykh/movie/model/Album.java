package com.codestorykh.movie.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.codestorykh.movie.constant.TableConstant.*;

/**
 * Represents an album entity containing metadata and relationships
 * with categories, licenses, and statistics.
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = T_ALBUM)
public class Album implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameEn;

    private String nameKh;

    // URL or path to the album logo image
    private String logo;

    // Album rating, e.g., 4.5 stars
    private double rate;

    // Label associated with the album
    private String label;

    // Expiration date of the label
    private LocalDateTime labelExpiredDate;

    // Price of the album
    private double price;

    // Publication status of the album
    private boolean published;

    @ToString.Exclude // Exclude from toString() to prevent infinite recursion
    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "album"
    )
    private List<AlbumCategory> albumCategories = new ArrayList<>();

    public void addAlbumCategory(AlbumCategory albumCategory) {
        albumCategories.add(albumCategory);
        albumCategory.setAlbum(this); // Maintain bidirectional relationship
    }

    public void removeAlbumCategory(AlbumCategory albumCategory) {
        albumCategories.remove(albumCategory);
        albumCategory.setAlbum(null); // Break bidirectional relationship
    }

    @ToString.Exclude
    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "album"
    )
    private List<AlbumLicense> albumLicenses = new ArrayList<>();

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(
            name = "video_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "video_album_fk")
    )
    private Video video;

    @ToString.Exclude
    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, // Cascade operations
            mappedBy = "album" // Field in Statistic that owns the relationship
    )
    private List<Statistic> statistics = new ArrayList<>();
}
