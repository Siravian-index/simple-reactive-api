package com.example.simplereactiveapi.recipe.usecases;

import com.example.simplereactiveapi.recipe.entity.Recipe;
import com.example.simplereactiveapi.recipe.repository.RecipeRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeleteRecipeUseCase {

    private final RecipeRepository repository;

    public DeleteRecipeUseCase(RecipeRepository repository) {
        this.repository = repository;
    }

    public Mono<Void> removeRecipe(String id) {
        return validateItExists(id)
                .flatMap(recipe -> repository.deleteById(recipe.getId()));

    }

    private Mono<Recipe> validateItExists(String id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(() -> new IllegalStateException("User does not exits " + id)));
    }
}
