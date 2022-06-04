package com.example.simplereactiveapi.recipe.usecases;

import com.example.simplereactiveapi.recipe.dto.RecipeDTO;
import com.example.simplereactiveapi.recipe.repository.RecipeRepository;
import com.example.simplereactiveapi.recipe.mapper.RecipeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class GetRecipesUseCase {
    private final RecipeRepository repository;
    private final RecipeMapper mapper;

    public Flux<RecipeDTO> apply() {
        return repository.findAll().map(mapper::toRecipeDTO);
    }

}
