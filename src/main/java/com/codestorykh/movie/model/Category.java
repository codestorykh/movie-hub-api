package com.codestorykh.movie.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.codestorykh.movie.constant.TableConstant.M_CATEGORY;

/**
 * Represents a category in the system, such as genres or classifications.
 * Each category can have multiple associated albums via the AlbumCategory entity.
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = M_CATEGORY)
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String description;

    @Column(name = "c_order")
    private int cOrder;

    // ID of the parent category, used for hierarchical structures
    @Column(name = "parent_id")
    private Long parentId;

    // Level of the category in a hierarchical structure
    private int level;

    @ToString.Exclude
    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE},
            mappedBy = "category"
    )
    private List<AlbumCategory> albumCategories = new ArrayList<>();

    public Category() {
    }

    public void addAlbumCategory(AlbumCategory albumCategory) {
        albumCategories.add(albumCategory);
        albumCategory.setCategory(this); // Ensure bidirectional relationship
    }

    public void removeAlbumCategory(AlbumCategory albumCategory) {
        albumCategories.remove(albumCategory);
        albumCategory.setCategory(null); // Clear the relationship
    }

    public boolean isChildCategory() {
        return parentId != null;
    }

    public boolean isRootCategory() {
        return parentId == null;
    }
}
