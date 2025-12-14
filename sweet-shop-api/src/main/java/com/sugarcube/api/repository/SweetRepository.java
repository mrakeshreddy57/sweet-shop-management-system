package com.sugarcube.api.repository;

import com.sugarcube.api.model.Sweet;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface SweetRepository extends MongoRepository<Sweet, String> {
    List<Sweet> findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(String name, String category);
}
