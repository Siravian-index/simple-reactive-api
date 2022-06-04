package com.example.simplereactiveapi.recipe.usecases;

import com.example.simplereactiveapi.recipe.repository.RecipeRepository;
import com.example.simplereactiveapi.recipe.validator.RecipeValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class DeleteRecipeUseCase {

    private final RecipeRepository repository;
    private final RecipeValidator validator;

    public Mono<Void> apply(String id) {
        return validator.validateItExists(id)
                .flatMap(recipe -> repository.deleteById(recipe.getId()));

    }

}
