package com.example.simplereactiveapi.recipe.usecases;

import com.example.simplereactiveapi.recipe.dto.RecipeDTO;
import com.example.simplereactiveapi.recipe.repository.RecipeRepository;
import com.example.simplereactiveapi.recipe.mapper.RecipeMapper;
import com.example.simplereactiveapi.recipe.validator.RecipeValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class PutRecipeUseCase {

    private final RecipeRepository repository;
    private final RecipeMapper mapper;
    private final RecipeValidator validator;

    public Mono<RecipeDTO> apply(RecipeDTO recipeDTO) {
        return validator.validateItExists(recipeDTO.getId())
                .flatMap(recipe -> repository.save(mapper.toEntity(recipeDTO)))
                .map(mapper::toRecipeDTO);
    }

}

