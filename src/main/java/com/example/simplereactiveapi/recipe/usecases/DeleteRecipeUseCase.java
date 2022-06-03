package com.example.simplereactiveapi.recipe.usecases;

import com.example.simplereactiveapi.recipe.RecipeDTO;
import com.example.simplereactiveapi.recipe.RecipeRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeleteRecipeUseCase {

    private final RecipeRepository repository;

    public DeleteRecipeUseCase(RecipeRepository repository) {
        this.repository = repository;
    }

    public Mono<Void> removeRecipe(String id) {
        return repository.deleteById(id);
    }
}
