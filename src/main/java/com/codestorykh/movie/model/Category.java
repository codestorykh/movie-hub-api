package com.codestorykh.movie.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Table(name = "m_category")
@Entity
public class Category implements Serializable {

    @Id
    @SequenceGenerator(
            name = "m_category_sequence",
            sequenceName = "m_category_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "m_category_sequence"
    )
    private Long id;

    private String code;
    private String description;
    private int cOrder;
    private Long parentId;
    private int level;

    @ToString.Exclude
    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE},
            mappedBy = "category"
    )

    private List<AlbumCategory> albumCategories = new ArrayList<>();

    public void addAlbumCategory(AlbumCategory albumCategory) {
        albumCategories.add(albumCategory);
    }

    public void removeAlbumCategory(AlbumCategory albumCategory) {
        this.albumCategories.remove(albumCategory);
    }
}
