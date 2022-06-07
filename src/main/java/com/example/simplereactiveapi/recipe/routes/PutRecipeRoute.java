package com.example.simplereactiveapi.recipe.routes;

import com.example.simplereactiveapi.recipe.dto.RecipeDTO;
import com.example.simplereactiveapi.recipe.entity.Recipe;
import com.example.simplereactiveapi.recipe.usecases.PostRecipeUseCase;
import com.example.simplereactiveapi.recipe.usecases.PutRecipeUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class PutRecipeRoute {
    @Bean
    @RouterOperation(path = "/v1/api/recipe/", produces = {
            MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.PUT, beanClass = PutRecipeUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "updateRecipe", responses = {
                    @ApiResponse(responseCode = "202", description = "successful operation", content = @Content(schema = @Schema(implementation = Recipe.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid Recipe details supplied")}
                    , requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = Recipe.class)))
            ))
    public RouterFunction<ServerResponse> putRecipe(PutRecipeUseCase update) {
        return route(PUT("/v1/api/recipe/").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(RecipeDTO.class)
                        .flatMap(update::apply)
                        .flatMap(recipeDTO -> ServerResponse.status(HttpStatus.ACCEPTED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(recipeDTO))
                        .onErrorResume(e -> ServerResponse.status(HttpStatus.BAD_REQUEST).build())
        );
    }
}
