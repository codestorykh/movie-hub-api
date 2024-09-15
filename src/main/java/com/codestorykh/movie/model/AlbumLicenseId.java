package com.codestorykh.movie.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Setter
@Getter
@Embeddable
public class AlbumLicenseId implements Serializable {

    @Column(name = "album_id")
    private Long albumId;

    @Column(name = "license_id")
    private Long licenseId;

    @Column(name = "user_id")
    private Long userId;

    public AlbumLicenseId(Long albumId, Long licenseId, Long userId) {
        this.albumId = albumId;
        this.licenseId = licenseId;
        this.userId = userId;
    }

    public AlbumLicenseId() {

    }

    @Override
    public String toString() {
        return "AlbumLicenseId{" +
                "albumId=" + albumId +
                ", licenseId=" + licenseId +
                ", userId=" + userId +
                '}';
    }
}
