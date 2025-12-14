package com.sugarcube.api.service;

import com.sugarcube.api.model.Sweet;
import com.sugarcube.api.repository.SweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SweetService {

    @Autowired
    private SweetRepository sweetRepository;

    public List<Sweet> getAllSweets() {
        return sweetRepository.findAll();
    }

    public Sweet addSweet(Sweet sweet) {
        return sweetRepository.save(sweet);
    }

    public Sweet purchaseSweet(String id) {
        Sweet sweet = sweetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sweet not found"));

        if (sweet.getQuantity() <= 0) {
            throw new RuntimeException("Out of stock");
        }

        sweet.setQuantity(sweet.getQuantity() - 1);
        return sweetRepository.save(sweet);
    }

    public Sweet restockSweet(String id, int amount) {
        Sweet sweet = sweetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sweet not found"));
        sweet.setQuantity(sweet.getQuantity() + amount);
        return sweetRepository.save(sweet);
    }

    public List<Sweet> search(String keyword) {
        return sweetRepository.findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(keyword, keyword);
    }
}