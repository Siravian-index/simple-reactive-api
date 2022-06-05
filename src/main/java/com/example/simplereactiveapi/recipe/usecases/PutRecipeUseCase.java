package com.example.simplereactiveapi.recipe.usecases;

import com.example.simplereactiveapi.recipe.dto.RecipeDTO;
import com.example.simplereactiveapi.recipe.mapper.RecipeMapper;
import com.example.simplereactiveapi.recipe.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Validated
public class PutRecipeUseCase {

    private final RecipeRepository repository;
    private final RecipeMapper mapper;

    public Mono<RecipeDTO> apply(RecipeDTO recipeDTO) {
        return repository.findById(recipeDTO.getId())
                .switchIfEmpty(Mono.error(() -> new IllegalStateException("Recipe does not exist: " + recipeDTO.getId())))
                .flatMap(recipe -> repository.save(mapper.toEntity(recipeDTO)))
                .map(mapper::toRecipeDTO);
    }

}

