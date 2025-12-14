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

    // Standard methods required for the rest of the app
    public List<Sweet> getAllSweets() { return sweetRepository.findAll(); }
    public Sweet addSweet(Sweet sweet) { return sweetRepository.save(sweet); }

    // --- THIS IS THE LOGIC WE ARE TESTING ---

    public Sweet purchaseSweet(String id) {
        // 1. Find the sweet in the DB
        Sweet sweet = sweetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sweet not found"));

        // 2. Check if stock is 0 (Passes Test Case 2)
        if (sweet.getQuantity() <= 0) {
            throw new RuntimeException("Out of stock");
        }

        // 3. Decrease quantity (Passes Test Case 1)
        sweet.setQuantity(sweet.getQuantity() - 1);

        // 4. Save updates
        return sweetRepository.save(sweet);
    }

    public Sweet restockSweet(String id, int amount) {
        Sweet sweet = sweetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sweet not found"));
        sweet.setQuantity(sweet.getQuantity() + amount);
        return sweetRepository.save(sweet);
    }

    public List<Sweet> search(String keyword) {
        // Uses the custom query we defined in the Repository earlier
        return sweetRepository.findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(keyword, keyword);
    }
}