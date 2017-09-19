package com.example.querydsl.repository;

import com.example.querydsl.domain.QTCity;
import com.example.querydsl.domain.QTHotel;
import com.example.querydsl.domain.TCity;
import com.example.querydsl.domain.THotel;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TCityRepositoryTest {
    @Autowired
    private TCityRepository tCityRepository;

    QTCity qtCity = QTCity.tCity;
    QTHotel qtHotel = QTHotel.tHotel;


    /**
     * 非动态查询建议使用Query注解
     */
    @Test
    public void findDynamic() {
        Predicate predicate = qtCity.id.longValue().lt(10)
                .and(qtCity.name.like("shanghai"));
        //分页排序
        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "id"));
        PageRequest pageRequest = new PageRequest(0, 10, sort);
        //查找结果
        Page<TCity> tCityPage = tCityRepository.findAll(predicate, pageRequest);
        log.warn("result: {}", tCityPage.getTotalElements());
    }

    //针对返回的是Object[]提供了一个很好地解决方案
    @Test
    public void findByLeftJoin() {
        QTCity qtCity = QTCity.tCity;
        QTHotel qtHotel = QTHotel.tHotel;
        Predicate predicate = qtCity.name.like("shanghai");
        List<Tuple> result = tCityRepository.findCityAndHotel(predicate);
        for (Tuple row : result) {
            TCity tCity = row.get(qtCity);
            THotel tHotel = row.get(qtHotel);
            log.warn("qtCity: {}", tCity);
            log.warn("qtHotel: {}", tHotel);
            System.out.println("--------------------");
        }
        System.out.println(result);
    }

    @Test
    public void findByLeftJoinPage() {
        QTCity qtCity = QTCity.tCity;
        QTHotel qtHotel = QTHotel.tHotel;
        Predicate predicate = qtCity.name.like("shanghai");
        PageRequest pageRequest = new PageRequest(0, 10);
        QueryResults<Tuple> result = tCityRepository.findCityAndHotelPage(predicate, pageRequest);
        for (Tuple row : result.getResults()) {
            System.out.println("qtCity:" + row.get(qtCity));
            System.out.println("qtHotel:" + row.get(qtHotel));
            System.out.println("--------------------");
        }
        System.out.println(result.getTotal());
    }
}