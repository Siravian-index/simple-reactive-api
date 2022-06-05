package com.example.simplereactiveapi.recipe.usecases;

import com.example.simplereactiveapi.recipe.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class DeleteRecipeUseCase {

    private final RecipeRepository repository;

    public Mono<Void> apply(String id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(() -> new IllegalStateException("Recipe not found " + id)))
                .flatMap(recipe -> repository.deleteById(recipe.getId()));

    }

}
