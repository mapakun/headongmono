package com.haedong.backend.dispatch;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dispatches")
@RequiredArgsConstructor
public class DispatchController {

    private final DispatchService dispatchService;

    @GetMapping
    public ResponseEntity<List<Dispatch>> findAll() {
        return ResponseEntity.ok(dispatchService.findAll());
    }

    @GetMapping("/{dispatchId}")
    public ResponseEntity<Dispatch> findById(@PathVariable String dispatchId) {
        return ResponseEntity.ok(dispatchService.findById(dispatchId));
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody Dispatch dispatch) {
        dispatchService.create(dispatch);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{dispatchId}")
    public ResponseEntity<Void> update(@PathVariable String dispatchId, @Valid @RequestBody Dispatch dispatch) {
        dispatch.setDispatchId(dispatchId);
        dispatchService.update(dispatch);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{dispatchId}")
    public ResponseEntity<Void> delete(@PathVariable String dispatchId) {
        dispatchService.delete(dispatchId);
        return ResponseEntity.ok().build();
    }
}
