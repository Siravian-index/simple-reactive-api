package com.example.simplereactiveapi.recipe.routes;

import com.example.simplereactiveapi.recipe.dto.RecipeDTO;
import com.example.simplereactiveapi.recipe.entity.Recipe;
import com.example.simplereactiveapi.recipe.mapper.RecipeMapper;
import com.example.simplereactiveapi.recipe.repository.RecipeRepository;
import com.example.simplereactiveapi.recipe.usecases.GetRecipesUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import static org.junit.jupiter.api.Assertions.*;



@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {GetRecipesRoute.class, GetRecipesUseCase.class, RecipeMapper.class})
class GetRecipesRouteTest {


    @MockBean
    private RecipeRepository repository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void bringsAllRecipes() {
        Recipe recipe1 = new Recipe();
        Recipe recipe2 = new Recipe();
        recipe1.setId("fakeID1");
        recipe1.setName("tacos");
        recipe1.setPrice(400);
        recipe2.setId("fakeID2");
        recipe2.setName("burger");
        recipe2.setPrice(300);

        Mockito.when(repository.findAll()).thenReturn(Flux.just(recipe1, recipe2));

        webTestClient.get()
                .uri("/v1/api/recipe/")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(RecipeDTO.class)
                .value(recipeDTOS -> {
                    Assertions.assertThat(recipeDTOS.get(0).getName()).isEqualTo(recipe1.getName());
                    Assertions.assertThat(recipeDTOS.get(1).getName()).isEqualTo(recipe2.getName());
                });
    }
}