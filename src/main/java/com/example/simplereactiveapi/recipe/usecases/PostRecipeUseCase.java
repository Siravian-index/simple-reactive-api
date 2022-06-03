package com.example.simplereactiveapi.recipe.usecases;

import com.example.simplereactiveapi.recipe.RecipeDTO;
import com.example.simplereactiveapi.recipe.RecipeRepository;
import com.example.simplereactiveapi.recipe.mapper.RecipeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class PostRecipeUseCase {
    private final RecipeRepository repository;
    private final RecipeMapper mapper;

    public PostRecipeUseCase(RecipeRepository repository, RecipeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    public Mono<RecipeDTO> postRecipe(RecipeDTO recipeDTO) {
        return validateDTO(recipeDTO)
                .onErrorResume(throwable -> {
                    log.info(throwable.getMessage());
                    return Mono.empty();
                })
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
