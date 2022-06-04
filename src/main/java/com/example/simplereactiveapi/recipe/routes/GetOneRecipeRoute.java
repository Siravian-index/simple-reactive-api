package com.example.simplereactiveapi.recipe.routes;

import com.example.simplereactiveapi.recipe.usecases.GetOneRecipeUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GetOneRecipeRoute {
    @Bean
    public RouterFunction<ServerResponse> oneRecipe(GetOneRecipeUseCase getOne) {
        return route(GET("/v1/api/recipe/{id}"),
                request -> getOne.apply(request.pathVariable("id"))
                        .flatMap(recipeDTO -> ServerResponse.status(HttpStatus.OK)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(recipeDTO))
                        .onErrorResume(e -> ServerResponse.status(HttpStatus.NOT_FOUND).build())

        );
    }
}
