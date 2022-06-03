package com.example.simplereactiveapi.recipe;


import com.example.simplereactiveapi.recipe.usecases.DeleteRecipeUseCase;
import com.example.simplereactiveapi.recipe.usecases.GetRecipesUseCase;
import com.example.simplereactiveapi.recipe.usecases.PostRecipeUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RecipeController {

    @Bean
    public RouterFunction<ServerResponse> allRecipes(GetRecipesUseCase get) {
        return route(GET("/v1/api/recipe/"), request -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromProducer(get.getRecipes(), RecipeDTO.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> postRecipe(PostRecipeUseCase post) {
        return route(POST("/v1/api/recipe/").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(RecipeDTO.class)
                        .flatMap(post::postRecipe)
                        .flatMap(recipeDTO -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(recipeDTO))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> deleteRecipe(DeleteRecipeUseCase remove) {
        return route(DELETE("/v1/api/recipe/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.status(HttpStatus.ACCEPTED)
                        .body(BodyInserters.fromProducer(remove.removeRecipe(request.pathVariable("id")), Void.class))
        );
    }


}
