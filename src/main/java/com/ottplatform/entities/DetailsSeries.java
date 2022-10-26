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
@Table(name = "details_series")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetailsSeries extends BaseEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7078219408755246679L;

    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content content;

    @Column(name = "session_number", length = 4)
    private Integer sessionNumber;

    @Column(name = "episode_number", length = 4)
    private Integer episodeNumber;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "summary", length = 1000)
    private String summary;

    @Column(name = "thumbnail_url", length = 250)
    private String thumbnailUrl;

    @Column(name = "video_code", length = 80)
    private String videoCode;

}
