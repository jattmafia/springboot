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
@Table(name = "details_content")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetailsContent extends BaseEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7979704606662946521L;

    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content content;

    @Column(name = "video_code", length = 80)
    private String videoCode;

    @Column(name = "director", length = 80)
    private String director;

    @Column(name = "roten_tomatoes_score", length = 10)
    private String rotenTomatoesScore;

}
