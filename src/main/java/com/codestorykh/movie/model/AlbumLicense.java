package com.codestorykh.movie.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Table(name = "t_album_license")
@Entity
public class AlbumLicense implements Serializable {

    @EmbeddedId
    private AlbumLicenseId albumLicenseId;

    private double price;

    private String status;

    @Column(name = "approved_by")
    private String approvedBy;


    @ManyToOne
    @MapsId("licenseId")
    @JoinColumn(name = "license_id",
            foreignKey = @ForeignKey(name = "license_id_fk")
    )
    private AccountLicense accountLicense;

    @ManyToOne
    @MapsId("albumId")
    @JoinColumn(name = "album_id",
            foreignKey = @ForeignKey(name = "album_id_fk")
    )
    private Album album;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id",
            foreignKey = @ForeignKey(name = "user_id_fk")
    )
    private User user;

    public AlbumLicense(AlbumLicenseId albumLicenseId, double price,
                        String status, String approvedBy,
                        AccountLicense accountLicense, Album album, User user) {
        this.albumLicenseId = albumLicenseId;
        this.price = price;
        this.status = status;
        this.approvedBy = approvedBy;
        this.accountLicense = accountLicense;
        this.album = album;
        this.user = user;
    }


}
