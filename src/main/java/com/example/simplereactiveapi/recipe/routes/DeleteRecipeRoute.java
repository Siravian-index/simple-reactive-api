package com.example.simplereactiveapi.recipe.routes;

import com.example.simplereactiveapi.recipe.usecases.DeleteRecipeUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DeleteRecipeRoute {
    @Bean
    public RouterFunction<ServerResponse> deleteRecipe(DeleteRecipeUseCase remove) {
        return route(DELETE("/v1/api/recipe/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> remove.apply(request.pathVariable("id"))
                        .flatMap((unused) -> ServerResponse.status(HttpStatus.ACCEPTED).build())
                        .onErrorResume(e -> ServerResponse.status(HttpStatus.NOT_FOUND).build())
        );
    }
}
