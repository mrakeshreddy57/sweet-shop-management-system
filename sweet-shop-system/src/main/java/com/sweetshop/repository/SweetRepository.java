package com.sweetshop.repository;

import com.sweetshop.model.Sweet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SweetRepository extends MongoRepository<Sweet, String> {
    // We can add custom search methods here later
}