package com.example.querydsl.service.impl;

import com.example.querydsl.domain.TCity;
import com.example.querydsl.repository.TCityRepository;
import com.example.querydsl.service.TCityService;
import org.springframework.beans.factory.annotation.Autowired;

public class TCityServiceImpl implements TCityService {
    @Autowired
    private TCityRepository tCityRepository;

    @Override
    public TCity getTCityById(Integer id) {
        return tCityRepository.findOne(id);
    }
}
