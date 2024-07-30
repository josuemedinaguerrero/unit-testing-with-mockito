package com.pokemonreview.api.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.dto.ReviewDto;
import com.pokemonreview.api.models.Pokemon;
import com.pokemonreview.api.models.Review;
import com.pokemonreview.api.repository.PokemonRepository;
import com.pokemonreview.api.repository.ReviewRepository;
import com.pokemonreview.api.service.impl.ReviewServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

  @Mock
  private ReviewRepository reviewRepository;

  @Mock
  private PokemonRepository pokemonRepository;

  @InjectMocks
  private ReviewServiceImpl reviewService;

  private Pokemon pokemon;
  private Review review;
  private ReviewDto reviewDto;
  private PokemonDto pokemonDto;

  @BeforeEach
  public void init() {
    pokemon = Pokemon.builder().name("Pikachu").type("Electric").build();
    pokemonDto = PokemonDto.builder().name("Pikachu").type("Electric").build();
    review = Review.builder().title("Title").content("Content").stars(5).build();
    reviewDto = ReviewDto.builder().title("Title").content("Content").stars(5).build();
  }

  @Test
  public void ReviewService_CreateReview_ReturnsReviewDto() {
    Mockito.when(pokemonRepository.findById(pokemon.getId())).thenReturn(Optional.ofNullable(pokemon));
    Mockito.when(reviewRepository.save(Mockito.any(Review.class))).thenReturn(review);

    ReviewDto savedReview = reviewService.createReview(review.getId(), reviewDto);

    Assertions.assertThat(savedReview).isNotNull();
  }

  @Test
  public void ReviewService_GetReviewsByPokemonId_ReturnReviewDto() {
    int reviewId = 1;

    Mockito.when(reviewRepository.findByPokemonId(reviewId)).thenReturn(Arrays.asList(review));

    List<ReviewDto> pokemonReturn = reviewService.getReviewsByPokemonId(reviewId);

    Assertions.assertThat(pokemonReturn).isNotNull();
  }

  @Test
  public void ReviewService_GetReviewById_ReturnReviewDto() {

  }

}
