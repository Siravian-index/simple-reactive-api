package com.example.simplereactiveapi.recipe;


import com.example.simplereactiveapi.recipe.usecases.GetRecipes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RecipeController {

    @Bean
    public RouterFunction<ServerResponse> allRecipes(GetRecipes getRecipes) {
        return route(GET("/"), request -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromProducer(getRecipes.getRecipes(), RecipeDTO.class))
        );
    }


}
