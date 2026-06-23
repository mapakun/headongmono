package com.haedong.backend.route;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RouteMapper {
    List<Route> findAll();
    Route findById(String routeNo);
    void insert(Route route);
    void update(Route route);
    void delete(String routeNo);
}
