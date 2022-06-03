package com.example.simplereactiveapi.recipe.usecases;

import com.example.simplereactiveapi.recipe.RecipeDTO;
import com.example.simplereactiveapi.recipe.RecipeRepository;
import com.example.simplereactiveapi.recipe.mapper.RecipeMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PostRecipe {
    private final RecipeRepository repository;
    private final RecipeMapper mapper;

    public PostRecipe(RecipeRepository repository, RecipeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Mono<RecipeDTO> postRecipe(RecipeDTO recipeDTO) {
        return repository.save(mapper.toEntity(recipeDTO))
                .map(mapper::toRecipeDTO);
    }

}
