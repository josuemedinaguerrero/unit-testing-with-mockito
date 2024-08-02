package com.pokemonreview.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemonreview.api.controllers.PokemonController;
import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.dto.ReviewDto;
import com.pokemonreview.api.models.Pokemon;
import com.pokemonreview.api.models.Review;
import com.pokemonreview.api.service.PokemonService;

@WebMvcTest(controllers = PokemonController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class PokemonControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private PokemonService pokemonService;

  private Pokemon pokemon;
  private PokemonDto pokemonDto;
  private Review review;
  private ReviewDto reviewDto;

  @BeforeEach
  public void init() {
    pokemon = Pokemon.builder().name("Pikachu").type("Electric").build();
    pokemonDto = PokemonDto.builder().name("Pikachu").type("Electric").build();
    review = Review.builder().title("Title").content("Content").stars(5).build();
    reviewDto = ReviewDto.builder().title("Title").content("Content").stars(5).build();
  }

  @Test
  public void PokemonController_CreatePokemon_ReturnCreated() throws Exception {
    BDDMockito.given(pokemonService.createPokemon(ArgumentMatchers.any()))
        .willAnswer((invocation -> invocation.getArgument(0)));

    ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/pokemon/create")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(pokemonDto)));

    response.andExpect(MockMvcResultMatchers.status().isCreated());
  }

}
