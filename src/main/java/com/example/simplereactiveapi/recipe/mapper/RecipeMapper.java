package com.example.simplereactiveapi.recipe.mapper;

import com.example.simplereactiveapi.recipe.Recipe;
import com.example.simplereactiveapi.recipe.RecipeDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RecipeMapper {

    private final ModelMapper modelMapper;

    public RecipeMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public RecipeDTO toRecipeDTO(Recipe recipe) {
        return modelMapper.map(recipe, RecipeDTO.class);
    }

    public Recipe toEntity(RecipeDTO dto) {
        return modelMapper.map(dto, Recipe.class);
    }

}
