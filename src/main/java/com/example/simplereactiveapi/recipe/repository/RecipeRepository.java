package com.example.simplereactiveapi.recipe.repository;

import com.example.simplereactiveapi.recipe.entity.Recipe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends ReactiveMongoRepository<Recipe, String> {
}
