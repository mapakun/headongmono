package com.haedong.backend.route;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @GetMapping
    public ResponseEntity<List<Route>> findAll() {
        return ResponseEntity.ok(routeService.findAll());
    }

    @GetMapping("/{routeNo}")
    public ResponseEntity<Route> findById(@PathVariable String routeNo) {
        return ResponseEntity.ok(routeService.findById(routeNo));
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody Route route) {
        routeService.create(route);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{routeNo}")
    public ResponseEntity<Void> update(@PathVariable String routeNo, @Valid @RequestBody Route route) {
        route.setRouteNo(routeNo);
        routeService.update(route);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{routeNo}")
    public ResponseEntity<Void> delete(@PathVariable String routeNo) {
        routeService.delete(routeNo);
        return ResponseEntity.ok().build();
    }
}
