package com.example.simplereactiveapi.recipe.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RecipeDTO {
    private String id;
    @NotBlank(message = "Name is required")
    private String name;
    @Min(value = 1, message = "Price must be greater than 0")
    @Max(value = 500, message = "Price cannot be greater than 500")
    @NotNull
    private Integer price;
}
