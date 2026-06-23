package com.haedong.backend.dispatch;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DispatchService {

    private final DispatchMapper dispatchMapper;

    public List<Dispatch> findAll() {
        return dispatchMapper.findAll();
    }

    public Dispatch findById(String dispatchId) {
        return dispatchMapper.findById(dispatchId);
    }

    public void create(Dispatch dispatch) {
        dispatch.setDispatchId(UUID.randomUUID().toString());
        dispatchMapper.insert(dispatch);
    }

    public void update(Dispatch dispatch) {
        dispatchMapper.update(dispatch);
    }

    public void delete(String dispatchId) {
        dispatchMapper.delete(dispatchId);
    }
}
