package com.example.simplereactiveapi.recipe.usecases;

import com.example.simplereactiveapi.recipe.dto.RecipeDTO;
import com.example.simplereactiveapi.recipe.repository.RecipeRepository;
import com.example.simplereactiveapi.recipe.mapper.RecipeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Validated
public class PostRecipeUseCase {
    private final RecipeRepository repository;
    private final RecipeMapper mapper;


    public Mono<RecipeDTO> apply(RecipeDTO recipeDTO) {
        return repository.save(mapper.toEntity(recipeDTO))
                .map(mapper::toRecipeDTO);
    }


}
