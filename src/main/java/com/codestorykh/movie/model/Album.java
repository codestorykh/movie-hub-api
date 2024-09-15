package com.codestorykh.movie.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Table(name = "t_album")
@Entity
public class Album implements Serializable {

    @Id
    @SequenceGenerator(
            name = "t_album_sequence",
            sequenceName = "t_album_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "t_album_sequence"
    )
    private Long id;

    private String nameEn;
    private String nameKh;
    private String logo;
    private double rate;
    private String label;
    private LocalDateTime labelExpiredDate;
    private double price;
    private boolean published;

    @ToString.Exclude
    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "album"
    )
    private List<AlbumCategory> albumCategories = new ArrayList<>();

    public void addAlbumCategory(AlbumCategory albumCategory) {
        albumCategories.add(albumCategory);
    }

    public void removeAlbumCategory(AlbumCategory albumCategory) {
        this.albumCategories.remove(albumCategory);
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
            foreignKey = @ForeignKey(
                    name = "video_album_fk"
            )
    )
    private Video video;

    @ToString.Exclude
    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "album"
    )
    private List<Statistic> statistics = new ArrayList<>();
}
