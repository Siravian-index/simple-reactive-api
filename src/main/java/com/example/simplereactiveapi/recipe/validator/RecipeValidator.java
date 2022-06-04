package com.example.simplereactiveapi.recipe.validator;

import com.example.simplereactiveapi.recipe.entity.Recipe;
import com.example.simplereactiveapi.recipe.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class RecipeValidator {

    private final RecipeRepository repository;

    public Mono<Recipe> validateItExists(String id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(() -> new IllegalStateException("Recipe does not exits. ID: " + id)));
    }
}
