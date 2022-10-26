package com.ottplatform.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "customer_watch_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerWatchHistory extends BaseEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5076330450038544094L;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content content;

    @Column(name = "episode")
    private Integer episode;

    @Column(name = "season")
    private Integer season;

    @Column(name = "last_duration", length = 10)
    private String lastDuration;

}
