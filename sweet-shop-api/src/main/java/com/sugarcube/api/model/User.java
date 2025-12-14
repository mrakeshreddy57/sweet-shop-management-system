package com.sugarcube.api.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.HashSet;
import java.util.Set;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;

    private String username;
    private String password;

    private Set<String> roles = new HashSet<>();

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}