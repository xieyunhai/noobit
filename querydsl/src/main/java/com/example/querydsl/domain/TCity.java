package com.example.querydsl.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "t_city")
public class TCity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String country;
    private String map;
}
