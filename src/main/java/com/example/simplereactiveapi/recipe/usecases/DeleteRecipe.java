package com.example.simplereactiveapi.recipe.usecases;

import com.example.simplereactiveapi.recipe.RecipeDTO;
import com.example.simplereactiveapi.recipe.RecipeRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeleteRecipe {

    private final RecipeRepository repository;

    public DeleteRecipe(RecipeRepository repository) {
        this.repository = repository;
    }

    public Mono<Void> removeRecipe(RecipeDTO recipeDTO) {
        return repository.deleteById(recipeDTO.getId());
    }
}
