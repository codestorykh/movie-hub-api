package com.codestorykh.movie.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.codestorykh.movie.constant.TableConstant.APPROVED_BY;
import static com.codestorykh.movie.constant.TableConstant.T_ACCOUNT_LICENSE;


@Getter
@Setter
@ToString
@Entity
@Table(name = T_ACCOUNT_LICENSE)
public class AccountLicense implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Type of the license (e.g., "Premium", "Standard")
    private String licenseType;

    // Price of the license
    private double price;

    // Current status of the license (e.g., "Active", "Expired")
    private String status;

    // Name or identifier of the person who approved the license
    @Column(name = APPROVED_BY)
    private String approvedBy;

    @ToString.Exclude
    @OneToMany(
            mappedBy = "accountLicense",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
        user.setAccountLicense(this);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.setAccountLicense(null); // Break bidirectional relationship
    }

    @ToString.Exclude
    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "accountLicense"
    )
    private List<AlbumLicense> albumLicenses = new ArrayList<>();

    public void addAlbumLicense(AlbumLicense albumLicense) {
        albumLicenses.add(albumLicense);
        albumLicense.setAccountLicense(this); // Maintain bidirectional relationship
    }

    public void removeAlbumLicense(AlbumLicense albumLicense) {
        albumLicenses.remove(albumLicense);
        albumLicense.setAccountLicense(null); // Break bidirectional relationship
    }
}
