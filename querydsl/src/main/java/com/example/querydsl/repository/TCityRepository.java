package com.example.querydsl.repository;

import com.example.querydsl.domain.TCity;

import com.example.querydsl.repository.custom.TCityRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface TCityRepository extends JpaRepository<TCity, Integer>, QueryDslPredicateExecutor<TCity>, TCityRepositoryCustom {
}
