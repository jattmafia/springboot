package com.ottplatform.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "content_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContentType extends BaseEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 906656642145674884L;

    @Column(name = "type", length = 60)
    private String type;

    @OneToMany(mappedBy = "contentType", cascade = CascadeType.ALL)
    private List<Content> contents;

}
