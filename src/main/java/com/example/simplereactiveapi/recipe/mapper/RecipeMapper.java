package com.example.simplereactiveapi.recipe.mapper;

import com.example.simplereactiveapi.recipe.dto.RecipeDTO;
import com.example.simplereactiveapi.recipe.entity.Recipe;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.EnableWebFlux;

@Component
@EnableWebFlux
public class RecipeMapper {

    private final ModelMapper modelMapper = new ModelMapper();


    public RecipeDTO toRecipeDTO(Recipe recipe) {
        return modelMapper.map(recipe, RecipeDTO.class);
    }

    public Recipe toEntity(RecipeDTO dto) {
        return modelMapper.map(dto, Recipe.class);
    }

}
