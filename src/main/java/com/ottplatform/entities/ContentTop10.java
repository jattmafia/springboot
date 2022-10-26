package com.ottplatform.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "content_top_10")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContentTop10 extends BaseEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6739069930317209666L;

    @OneToOne
    @JoinColumn(name = "content_id")
    private Content content;

}
