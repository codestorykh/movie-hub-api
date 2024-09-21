package com.codestorykh.movie.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a category in the system, such as genres or classifications.
 * Each category can have multiple associated albums via the AlbumCategory entity.
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "m_category")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Unique code for the category
    private String code;

    // Description of the category
    private String description;

    // Custom order for sorting categories
    @Column(name = "c_order")
    private int cOrder;

    // ID of the parent category, used for hierarchical structures
    @Column(name = "parent_id")
    private Long parentId;

    // Level of the category in a hierarchical structure
    private int level;

    /**
     * List of AlbumCategory entities associated with this category.
     *
     * - @OneToMany: One Category can have many AlbumCategory associations.
     * - cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}:
     *   Specifies the cascade operations that will propagate to AlbumCategory entities.
     * - mappedBy: Specifies that the 'category' field in AlbumCategory is the owning side.
     */
    @ToString.Exclude
    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE},
            mappedBy = "category"
    )
    private List<AlbumCategory> albumCategories = new ArrayList<>();

    /**
     * Default constructor required by JPA.
     */
    public Category() {
    }

    /**
     * Adds an AlbumCategory to this Category's list.
     *
     * @param albumCategory The AlbumCategory entity to be added.
     */
    public void addAlbumCategory(AlbumCategory albumCategory) {
        albumCategories.add(albumCategory);
        albumCategory.setCategory(this); // Ensure bidirectional relationship
    }

    /**
     * Removes an AlbumCategory from this Category's list.
     *
     * @param albumCategory The AlbumCategory entity to be removed.
     */
    public void removeAlbumCategory(AlbumCategory albumCategory) {
        albumCategories.remove(albumCategory);
        albumCategory.setCategory(null); // Clear the relationship
    }

    /**
     * Utility method to check if the category has child categories based on parentId.
     *
     * @return True if this category has a parent, indicating it's a child; false otherwise.
     */
    public boolean isChildCategory() {
        return parentId != null;
    }

    /**
     * Utility method to check if the category is a root category (no parent).
     *
     * @return True if this category is a root (has no parent), false otherwise.
     */
    public boolean isRootCategory() {
        return parentId == null;
    }
}
