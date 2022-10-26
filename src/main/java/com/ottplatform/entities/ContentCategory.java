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
@Table(name = "content_category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContentCategory extends BaseEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4906628968971260308L;

    @Column(name = "category", length = 60)
    private String category;

    @OneToMany(mappedBy = "contentCategory", cascade = CascadeType.ALL)
    private List<Content> contents;

}
