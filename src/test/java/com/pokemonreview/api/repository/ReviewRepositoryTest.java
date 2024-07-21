package com.pokemonreview.api.repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.pokemonreview.api.models.Review;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ReviewRepositoryTest {

  @Autowired
  private ReviewRepository reviewRepository;

  @Test
  public void ReviewRepository_SaveAll_ReturnsSavedReview() {
    Review review = Review.builder().title("title").content("Content").stars(5).build();

    Review savedReview = reviewRepository.save(review);

    Assertions.assertThat(savedReview).isNotNull();
    Assertions.assertThat(savedReview.getId()).isGreaterThan(0);
  }

  @Test
  public void ReviewRepository_GetAll_ReturnsMoreThenOneReview() {
    Review review = Review.builder().title("title").content("Content").stars(5).build();
    Review review2 = Review.builder().title("title").content("Content").stars(5).build();

    reviewRepository.save(review);
    reviewRepository.save(review2);

    List<Review> reviews = reviewRepository.findAll();

    Assertions.assertThat(reviews).isNotNull();
    Assertions.assertThat(reviews.size()).isEqualTo(2);
  }

}
