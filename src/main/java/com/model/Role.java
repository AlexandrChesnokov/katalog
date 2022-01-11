package com.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "roles1")
@Getter
@Setter
public class Role {

    @Id
    private int id;

    @Column(name = "name")
    private String name;



}
