package com.example.simplereactiveapi.recipe.usecases;

import com.example.simplereactiveapi.recipe.dto.RecipeDTO;
import com.example.simplereactiveapi.recipe.repository.RecipeRepository;
import com.example.simplereactiveapi.recipe.mapper.RecipeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class PostRecipeUseCase {
    private final RecipeRepository repository;
    private final RecipeMapper mapper;


    public Mono<RecipeDTO> apply(RecipeDTO recipeDTO) {
        return validateDTO(recipeDTO)
                .flatMap(recipeDTO1 -> repository.save(mapper.toEntity(recipeDTO1)))
                .map(mapper::toRecipeDTO);
    }


    private Mono<RecipeDTO> validateDTO(RecipeDTO recipeDTO) {
        return Mono.just(recipeDTO).filter(this::validateProperties)
                .switchIfEmpty(Mono.error(() -> new IllegalStateException("Missing properties")));
    }

    private boolean validateProperties(RecipeDTO dto) {
        return dto.getName() != null && dto.getPrice() != null;
    }

}
