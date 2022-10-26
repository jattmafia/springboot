package com.ottplatform.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "content")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Content extends BaseEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1430660078947737765L;

    @Column(name = "title", length = 100)
    private String title;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ContentCategory contentCategory;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private ContentType contentType;

    @Column(name = "summary", length = 1000)
    private String summary;

    @Column(name = "logo_url", length = 250)
    private String logoUrl;

    @Column(name = "bannerUrl", length = 250)
    private String bannerUrl;

    @Column(name = "thumbnail_url", length = 250)
    private String thumbnailUrl;

    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL)
    private List<DetailsContent> detailsContents;

    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL)
    private List<DetailsSeries> detailsSeries;

    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL)
    private List<CustomerWatchHistory> customerWatchHistories;

    @OneToOne(mappedBy = "content")
    private ContentTop10 contentTop10;
}
