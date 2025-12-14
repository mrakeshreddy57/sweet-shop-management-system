package com.sweetshop.service;

import com.sweetshop.model.Sweet;
import com.sweetshop.repository.SweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SweetService {

    @Autowired
    private SweetRepository sweetRepository;

    // Method to add a new sweet
    public Sweet addSweet(Sweet sweet) {
        return sweetRepository.save(sweet);
    }

    // Method to get all sweets
    public List<Sweet> getAllSweets() {
        return sweetRepository.findAll();
    }
}