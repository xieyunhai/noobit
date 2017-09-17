package com.example.querydsl.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "t_hotel")
public class THotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String address;
    private Integer city;
}
