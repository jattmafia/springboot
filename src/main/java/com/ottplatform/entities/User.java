package com.ottplatform.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = { "username", "email_id", "mobile_no" }) })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 8770477414967045581L;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "username", length = 80, unique = true)
    private String username;

    @Column(name = "email_id", length = 80, unique = true)
    private String emailId;

    @Column(name = "mobile_no", length = 15, unique = true)
    private String mobileNo;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "type", length = 60)
    private String type;

    @Column(name = "password", length = 100)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CustomerWatchHistory> customerWatchHistories;

}
