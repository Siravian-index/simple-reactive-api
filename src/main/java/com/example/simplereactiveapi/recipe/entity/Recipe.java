package com.example.simplereactiveapi.recipe.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "recipe")
public class Recipe {
    @Id
    private String id;
    private String name;
    private Integer price;
}
