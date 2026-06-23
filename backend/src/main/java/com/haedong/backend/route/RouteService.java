package com.haedong.backend.route;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteMapper routeMapper;

    public List<Route> findAll() {
        return routeMapper.findAll();
    }

    public Route findById(String routeNo) {
        return routeMapper.findById(routeNo);
    }

    public void create(Route route) {
        routeMapper.insert(route);
    }

    public void update(Route route) {
        routeMapper.update(route);
    }

    public void delete(String routeNo) {
        routeMapper.delete(routeNo);
    }
}
