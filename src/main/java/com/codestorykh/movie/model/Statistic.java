package com.codestorykh.movie.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "m_statistic")
@Entity
public class Statistic {

    @EmbeddedId
    private StatisticId statisticId;

    @Column(name = "click")
    private int click;

    @Column(name = "duration")
    private double duration;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id",
            foreignKey = @ForeignKey(name = "user_id_fk")
    )
    private User user;

    @ManyToOne
    @MapsId("albumId")
    @JoinColumn(name = "album_id",
            foreignKey = @ForeignKey(name = "album_id_fk")
    )
    private Album album;
}
