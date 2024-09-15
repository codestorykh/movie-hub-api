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
@Table(name = "t_account_license")
@Entity
public class AccountLicense implements Serializable {

    @Id
    @SequenceGenerator(
            name = "t_account_license_sequence",
            sequenceName = "t_account_license_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "t_account_license_sequence"
    )
    private Long id;

    private String licenseType;

    private double price;

    private String status;

    @Column(name = "approved_by")
    private String approvedBy;

    @ToString.Exclude
    @OneToMany(
            mappedBy = "accountLicense",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true,
            fetch = FetchType.LAZY // lazy loading when we get student we don't get books
    )
    private List<User> users = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "accountLicense"
    )
    private List<AlbumLicense> albumLicenses = new ArrayList<>();
}
