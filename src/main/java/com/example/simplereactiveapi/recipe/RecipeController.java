package com.example.simplereactiveapi.recipe;


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



    //Two options:
    //1
        /*return route(POST("/save/patient").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(PatientDTO.class)
                .flatMap(savePatientUseCase::apply)
                .flatMap(result -> ServerResponse.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(result))
        );*/

    //2
//    Function<PatientDTO, Mono<ServerResponse>> executeGuardar = patientDTO -> savePatientUseCase.apply(patientDTO)
//            .flatMap(result -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(result));
//
//        return route(POST("/save/patient").and(accept(MediaType.APPLICATION_JSON)), request -> request.bodyToMono(PatientDTO.class).flatMap(executeGuardar));


}
