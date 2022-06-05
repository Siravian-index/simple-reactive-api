package com.example.simplereactiveapi.recipe.usecases;

import com.example.simplereactiveapi.recipe.dto.RecipeDTO;
import com.example.simplereactiveapi.recipe.mapper.RecipeMapper;
import com.example.simplereactiveapi.recipe.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class GetOneRecipeUseCase {
    private final RecipeRepository repository;
    private final RecipeMapper mapper;


    public Mono<RecipeDTO> apply(String id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(() -> new IllegalStateException("Recipe not found " + id)))
                .map(mapper::toRecipeDTO);
    }



}
