package com.sugarcube.api;

import com.sugarcube.api.model.Sweet;
import com.sugarcube.api.repository.SweetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final SweetRepository sweetRepository;

    public DataLoader(SweetRepository sweetRepository) {
        this.sweetRepository = sweetRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Only load data if the database is empty
        if (sweetRepository.count() == 0) {
            System.out.println("🍬 Loading initial sweets into the database...");

            sweetRepository.save(new Sweet("Galaxy Chocolate", "Chocolates", 2.50, 20));
            sweetRepository.save(new Sweet("Rainbow Lollipop", "Hard Candy", 1.00, 50));
            sweetRepository.save(new Sweet("Sour Worms", "Gummies", 3.00, 15));
            sweetRepository.save(new Sweet("Magic Mints", "Mints", 1.50, 0)); // Out of stock example
            sweetRepository.save(new Sweet("Fairy Floss", "Cotton Candy", 4.00, 10));

            System.out.println("✅ Sweets loaded!");
        }
    }
}