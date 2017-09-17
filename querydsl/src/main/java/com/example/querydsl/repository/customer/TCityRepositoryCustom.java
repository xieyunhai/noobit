package com.example.querydsl.repository.customer;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TCityRepositoryCustom {
    public List<Tuple> findCityAndHotel(Predicate predicate);

    public QueryResults<Tuple> findCityAndHotelPage(Predicate predicate, Pageable pageable);
}
