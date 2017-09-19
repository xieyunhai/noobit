package com.example.querydsl.repository.custom;

import com.example.querydsl.domain.QTCity;
import com.example.querydsl.domain.QTHotel;
import com.example.querydsl.repository.BaseRepository;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class TCityRepositoryImpl extends BaseRepository implements TCityRepositoryCustom {
    @Override
    public List<Tuple> findCityAndHotel(Predicate predicate) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QTCity tCity = QTCity.tCity;
        QTHotel tHotel = QTHotel.tHotel;
        JPAQuery<Tuple> jpaQuery = queryFactory.select(tCity, tHotel)
                .from(tCity, tHotel)
                .where(tHotel.city.longValue().eq(tCity.id.longValue()));
        jpaQuery.where(predicate);

        return jpaQuery.fetch();
    }

    @Override
    public QueryResults<Tuple> findCityAndHotelPage(Predicate predicate, Pageable pageable) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QTCity tCity = QTCity.tCity;
        QTHotel tHotel = QTHotel.tHotel;
        JPAQuery<Tuple> jpaQuery = queryFactory.select(tCity.id, tHotel)
                .from(tCity, tHotel)
                .where(tHotel.city.longValue().eq(tCity.id.longValue()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        return jpaQuery.fetchResults();
    }
}
