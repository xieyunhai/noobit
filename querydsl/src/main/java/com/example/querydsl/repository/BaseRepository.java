package com.example.querydsl.repository;

import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@NoRepositoryBean
public class BaseRepository {
    @PersistenceContext(unitName = "readPersistenceUnit")
    protected EntityManager em;
}
