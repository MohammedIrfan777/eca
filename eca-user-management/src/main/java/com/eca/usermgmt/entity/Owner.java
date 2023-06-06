package com.eca.usermgmt.entity;

import com.eca.usermgmt.constants.UserConstants;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Data
@JsonTypeName(UserConstants.TYPE_OWNER)
public class Owner extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "type")
    private String type;

}
