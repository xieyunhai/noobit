package com.example.querydsl.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dom4j.tree.AbstractEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "t_hotel")
public class THotel extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String address;
    private Integer city;
}
