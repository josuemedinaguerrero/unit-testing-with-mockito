package com.pokemonreview.api.service;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.dto.PokemonResponse;
import com.pokemonreview.api.models.Pokemon;
import com.pokemonreview.api.repository.PokemonRepository;
import com.pokemonreview.api.service.impl.PokemonServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PokemonServiceTest {

  @Mock
  private PokemonRepository pokemonRepository;

  @InjectMocks
  private PokemonServiceImpl pokemonService;

  @Test
  public void PokemonService_CreatePokemon_ReturnsPokemonDto() {
    Pokemon pokemon = Pokemon.builder().name("Pikachu").type("Electric").build();
    PokemonDto pokemonDto = PokemonDto.builder().name("Pikachu").type("Electric").build();

    Mockito.when(pokemonRepository.save(Mockito.any(Pokemon.class))).thenReturn(pokemon);

    PokemonDto savedPokemon = pokemonService.createPokemon(pokemonDto);

    Assertions.assertThat(savedPokemon).isNotNull();
  }

  @Test
  public void PokemonService_GetAllPokemon_ReturnsResponseDto() {
    @SuppressWarnings("unchecked")
    Page<Pokemon> pokemons = Mockito.mock(Page.class);

    Mockito.when(pokemonRepository.findAll(Mockito.any(Pageable.class))).thenReturn(pokemons);

    PokemonResponse savedPokemon = pokemonService.getAllPokemon(1, 10);

    Assertions.assertThat(savedPokemon).isNotNull();
  }

  @Test
  public void PokemonService_GetPokemonById_ReturnsPokemonDto() {
    Pokemon pokemon = Pokemon.builder().name("Pikachu").type("Electric").build();

    Mockito.when(pokemonRepository.findById(1)).thenReturn(Optional.ofNullable(pokemon));

    PokemonDto savedPokemon = pokemonService.getPokemonById(1);

    Assertions.assertThat(savedPokemon).isNotNull();
  }

  @Test
  public void PokemonService_UpdatePokemon_ReturnsPokemonDto() {
    Pokemon pokemon = Pokemon.builder().name("Pikachu").type("Electric").build();
    PokemonDto pokemonDto = PokemonDto.builder().name("Pikachu").type("Electric").build();

    Mockito.when(pokemonRepository.findById(1)).thenReturn(Optional.ofNullable(pokemon));
    Mockito.when(pokemonRepository.save(Mockito.any(Pokemon.class))).thenReturn(pokemon);

    PokemonDto savedPokemon = pokemonService.updatePokemon(pokemonDto, 1);

    Assertions.assertThat(savedPokemon).isNotNull();
  }

}
