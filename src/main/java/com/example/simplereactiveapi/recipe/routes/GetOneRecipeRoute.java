package com.example.simplereactiveapi.recipe.routes;

import com.example.simplereactiveapi.recipe.entity.Recipe;
import com.example.simplereactiveapi.recipe.usecases.GetOneRecipeUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GetOneRecipeRoute {
    @Bean
    @RouterOperation(path = "/v1/api/recipe/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetOneRecipeUseCase.class, method = RequestMethod.GET, beanMethod = "apply",
            operation = @Operation(operationId = "getOneRecipe", responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation",
                            content = @Content(schema = @Schema(implementation = Recipe.class))),
                    @ApiResponse(responseCode = "404", description = "Invalid Recipe id supplied")},
                    parameters = {@Parameter(in = ParameterIn.PATH, name = "id")}
            ))
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
