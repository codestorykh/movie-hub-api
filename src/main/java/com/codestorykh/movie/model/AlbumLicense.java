package com.codestorykh.movie.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents the relationship between an Album and a License, with an associated User.
 * This class uses a composite key to uniquely identify each relationship.
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "t_album_license")
public class AlbumLicense implements Serializable {

    /**
     * Composite primary key for the AlbumLicense entity.
     * Contains 'albumId', 'licenseId', and 'userId'.
     */
    @EmbeddedId
    private AlbumLicenseId albumLicenseId;

    // Price associated with this license for the album
    private double price;

    // Status of the license (e.g., "Active", "Expired")
    private String status;

    // Name or identifier of the person who approved this license
    @Column(name = "approved_by")
    private String approvedBy;

    /**
     * Reference to the AccountLicense entity.
     *
     * - @ManyToOne: Many AlbumLicense entities can be associated with a single AccountLicense.
     * - @MapsId("licenseId"): Maps the 'licenseId' part of AlbumLicenseId to this entity.
     * - @JoinColumn: Defines the foreign key column and constraint for 'license_id'.
     */
    @ManyToOne
    @MapsId("licenseId") // Maps the 'licenseId' part of AlbumLicenseId to AccountLicense
    @JoinColumn(
            name = "license_id", // Name of the foreign key column in the database
            foreignKey = @ForeignKey(name = "license_id_fk") // Custom foreign key constraint name
    )
    private AccountLicense accountLicense;

    /**
     * Reference to the Album entity.
     *
     * - @ManyToOne: Many AlbumLicense entities can be associated with a single Album.
     * - @MapsId("albumId"): Maps the 'albumId' part of AlbumLicenseId to this entity.
     * - @JoinColumn: Defines the foreign key column and constraint for 'album_id'.
     */
    @ManyToOne
    @MapsId("albumId") // Maps the 'albumId' part of AlbumLicenseId to Album
    @JoinColumn(
            name = "album_id", // Name of the foreign key column in the database
            foreignKey = @ForeignKey(name = "album_id_fk") // Custom foreign key constraint name
    )
    private Album album;

    /**
     * Reference to the User entity.
     *
     * - @ManyToOne: Many AlbumLicense entities can be associated with a single User.
     * - @MapsId("userId"): Maps the 'userId' part of AlbumLicenseId to this entity.
     * - @JoinColumn: Defines the foreign key column and constraint for 'user_id'.
     */
    @ManyToOne
    @MapsId("userId") // Maps the 'userId' part of AlbumLicenseId to User
    @JoinColumn(
            name = "user_id", // Name of the foreign key column in the database
            foreignKey = @ForeignKey(name = "user_id_fk") // Custom foreign key constraint name
    )
    private User user;

    /**
     * Default constructor required by JPA.
     */
    public AlbumLicense() {
    }

    /**
     * Constructor for creating an AlbumLicense instance with all fields initialized.
     *
     * @param albumLicenseId  Composite primary key consisting of album, license, and user IDs.
     * @param price           Price associated with this license.
     * @param status          Status of the license.
     * @param approvedBy      Identifier of the person who approved the license.
     * @param accountLicense  The AccountLicense entity associated with this AlbumLicense.
     * @param album           The Album entity associated with this AlbumLicense.
     * @param user            The User entity associated with this AlbumLicense.
     */
    public AlbumLicense(AlbumLicenseId albumLicenseId, double price, String status,
                        String approvedBy, AccountLicense accountLicense, Album album, User user) {
        this.albumLicenseId = albumLicenseId;
        this.price = price;
        this.status = status;
        this.approvedBy = approvedBy;
        this.accountLicense = accountLicense;
        this.album = album;
        this.user = user;
    }

    /**
     * Utility method to associate this AlbumLicense with an AccountLicense.
     *
     * @param accountLicense The AccountLicense entity to associate.
     */
    public void assignAccountLicense(AccountLicense accountLicense) {
        this.accountLicense = accountLicense;
        accountLicense.getAlbumLicenses().add(this);
    }

    /**
     * Utility method to associate this AlbumLicense with an Album.
     *
     * @param album The Album entity to associate.
     */
    public void assignAlbum(Album album) {
        this.album = album;
        album.getAlbumLicenses().add(this);
    }

    /**
     * Utility method to associate this AlbumLicense with a User.
     *
     * @param user The User entity to associate.
     */
    public void assignUser(User user) {
        this.user = user;
        user.getAlbumLicenses().add(this);
    }


    @Setter
    @Getter
    @Embeddable
    public static class AlbumLicenseId implements Serializable {

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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AlbumLicenseId that = (AlbumLicenseId) o;
            return Objects.equals(albumId, that.albumId) && Objects.equals(licenseId, that.licenseId) && Objects.equals(userId, that.userId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(albumId, licenseId, userId);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumLicense that = (AlbumLicense) o;
        return Double.compare(price, that.price) == 0 && Objects.equals(albumLicenseId, that.albumLicenseId) && Objects.equals(status, that.status) && Objects.equals(approvedBy, that.approvedBy) && Objects.equals(accountLicense, that.accountLicense) && Objects.equals(album, that.album) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(albumLicenseId, price, status, approvedBy, accountLicense, album, user);
    }
}
