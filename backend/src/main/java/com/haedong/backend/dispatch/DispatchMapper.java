package com.haedong.backend.dispatch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DispatchMapper {
    List<Dispatch> findAll();
    Dispatch findById(String dispatchId);
    void insert(Dispatch dispatch);
    void update(Dispatch dispatch);
    void delete(String dispatchId);
}
