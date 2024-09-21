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

@Getter
@Setter
@ToString
@Table(name = T_USER)
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
