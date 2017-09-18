package com.example.querydsl.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dom4j.tree.AbstractEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "t_city")
public class TCity extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String country;
    private String map;
}
