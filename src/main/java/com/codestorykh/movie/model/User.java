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
@Table(name = "t_user")
@Entity
public class User implements Serializable {

    @Id
    @SequenceGenerator(
            name = "t_user_sequence",
            sequenceName = "t_user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "t_user_sequence"
    )
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String username;

    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(
            name = "license_started_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime licenseStartedAt;

    @Column(
            name = "license_end_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime licenseEndAt;


    @Column(
            name = "image_url",
            columnDefinition = "TEXT"
    )
    private String imageUrl;


    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "user_account_license_fk"
            )
    )
    private AccountLicense accountLicense;

    @ToString.Exclude
    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "user"
    )
    private List<AlbumLicense> albumLicenses = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "user"
    )
    private List<Statistic> statistics = new ArrayList<>();
}
