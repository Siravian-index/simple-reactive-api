package com.example.simplereactiveapi.recipe;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends ReactiveMongoRepository<Recipe, String> {
}
