package com.example.simplereactiveapi.recipe.usecases;

import com.example.simplereactiveapi.recipe.RecipeDTO;
import com.example.simplereactiveapi.recipe.RecipeRepository;
import com.example.simplereactiveapi.recipe.mapper.RecipeMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
//@Validated
public class GetRecipesUseCase {
    private final RecipeRepository repository;
    private final RecipeMapper mapper;

    public GetRecipesUseCase(RecipeRepository repository, RecipeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Flux<RecipeDTO> getRecipes() {
        return repository.findAll().map(mapper::toRecipeDTO);
    }

}
