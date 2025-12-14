package com.sugarcube.api.controller;

import com.sugarcube.api.model.Sweet;
import com.sugarcube.api.service.SweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600) // Allows React to talk to Java
@RestController
@RequestMapping("/api/sweets")
public class SweetController {

    @Autowired
    private SweetService sweetService;

    // Public: Anyone can see the menu
    @GetMapping
    public List<Sweet> getAllSweets() {
        return sweetService.getAllSweets();
    }

    // Public: Search functionality
    @GetMapping("/search")
    public List<Sweet> search(@RequestParam String query) {
        return sweetService.search(query);
    }

    // Protected: Only Admin can add sweets
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Sweet addSweet(@RequestBody Sweet sweet) {
        return sweetService.addSweet(sweet);
    }

    // Protected: User or Admin can buy
    @PostMapping("/{id}/purchase")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> purchase(@PathVariable String id) {
        try {
            return ResponseEntity.ok(sweetService.purchaseSweet(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Protected: Only Admin can restock
    @PostMapping("/{id}/restock")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> restock(@PathVariable String id, @RequestBody int amount) {
        return ResponseEntity.ok(sweetService.restockSweet(id, amount));
    }
}