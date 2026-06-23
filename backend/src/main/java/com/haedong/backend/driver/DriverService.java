package com.haedong.backend.driver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverMapper driverMapper;

    public List<Driver> findAll() {
        return driverMapper.findAll();
    }

    public Driver findById(String id) {
        return driverMapper.findById(id);
    }

    public void create(Driver driver) {
        driverMapper.insert(driver);
    }

    public void update(Driver driver) {
        driverMapper.update(driver);
    }

    public void delete(String id) {
        driverMapper.delete(id);
    }
}
