package com.haedong.backend.driver;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DriverMapper {
    List<Driver> findAll();
    Driver findById(String id);
    void insert(Driver driver);
    void update(Driver driver);
    void delete(String id);
}
