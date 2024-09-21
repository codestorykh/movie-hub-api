package com.codestorykh.movie.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

import static com.codestorykh.movie.constant.TableConstant.*;

@Getter
@Setter
@ToString
@Table(name = M_STATISTIC)
@Entity
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = CLICK)
    private int click;

    @Column(name = DURATION)
    private double duration;

    // Many statistics can be associated with one user.
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = USER_ID, referencedColumnName = ID)
    private User user;

    // Many statistics can be associated with one album.
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = ALBUM_ID, referencedColumnName = ID)
    private Album album;

    @Setter
    @Getter
    @Embeddable
    public static class StatisticId implements Serializable {

        @Column(name = "user_id")
        private Long userId;

        @Column(name = "album_id")
        private Long albumId;

        public StatisticId() {

        }

        public StatisticId(Long userId, Long albumId) {
            this.userId = userId;
            this.albumId = albumId;
        }

        @Override
        public String toString() {
            return "StatisticId{" +
                    "userId=" + userId +
                    ", albumId=" + albumId +
                    '}';
        }
    }
}
