package com.example.simplereactiveapi.recipe.usecases;

import com.example.simplereactiveapi.recipe.repository.RecipeRepository;
import com.example.simplereactiveapi.recipe.mapper.RecipeMapper;
import org.springframework.stereotype.Service;

@Service
public class GetOneRecipeUseCase {
    private final RecipeRepository repository;
    private final RecipeMapper mapper;

    public GetOneRecipeUseCase(RecipeRepository repository, RecipeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
}
