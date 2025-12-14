package com.sweetshop.controller;

import com.sweetshop.model.Sweet;
import com.sweetshop.service.SweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sweets")
@CrossOrigin(origins = "*") // Allows the frontend to talk to this later
public class SweetController {

    @Autowired
    private SweetService sweetService;

    @PostMapping
    public Sweet createSweet(@RequestBody Sweet sweet) {
        return sweetService.addSweet(sweet);
    }

    @GetMapping
    public List<Sweet> getAllSweets() {
        return sweetService.getAllSweets();
    }
}