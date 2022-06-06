package com.example.simplereactiveapi.recipe.routes;

import com.example.simplereactiveapi.recipe.dto.RecipeDTO;
import com.example.simplereactiveapi.recipe.entity.Recipe;
import com.example.simplereactiveapi.recipe.mapper.RecipeMapper;
import com.example.simplereactiveapi.recipe.repository.RecipeRepository;
import com.example.simplereactiveapi.recipe.usecases.GetRecipesUseCase;
import com.example.simplereactiveapi.recipe.usecases.PostRecipeUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PostRecipeRoute.class, PostRecipeUseCase.class, RecipeMapper.class})
class PostRecipeRouteTest {
    @MockBean
    private RecipeRepository repository;

    @Autowired
    private WebTestClient webTestClient;


    @Test
    void createRecipeSuccessfully() {
//        given
        Recipe recipe = new Recipe();
        recipe.setId("fakeRecipeID");
        recipe.setName("sandwich");
        recipe.setPrice(400);

        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setId(recipe.getId());
        recipeDTO.setName(recipe.getName());
        recipeDTO.setPrice(recipe.getPrice());

        Mono<Recipe> recipeMono = Mono.just(recipe);

//        when
        Mockito.when(repository.save(recipe)).thenReturn(recipeMono);
//        assert
        webTestClient.post()
                .uri("/v1/api/recipe/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(recipeDTO), RecipeDTO.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(RecipeDTO.class)
                .value(recipeDTO1 -> {
                    Assertions.assertThat(recipeDTO1.getId()).isEqualTo(recipe.getId());
                });

    }

}