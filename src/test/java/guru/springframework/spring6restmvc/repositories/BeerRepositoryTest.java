package guru.springframework.spring6restmvc.repositories;

import guru.springframework.spring6restmvc.entities.Beer;
import guru.springframework.spring6restmvc.models.BeerStyle;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testSavedBeerTooLongName() {

        assertThrows(ConstraintViolationException.class, () -> {
            Beer savedBeer = beerRepository.save(Beer.builder()
                    .beerName("CharactersCharactersCharactersCharactersCharactersCharactersCharactersCharactersCharactersCharactersCharacters")
                    .beerStyle(BeerStyle.ROAR)
                    .upc("AAA")
                    .price(new BigDecimal(1))
                    .build());

            beerRepository.flush();
        });

//        assertThat(savedBeer).isNotNull();
//        assertThat(savedBeer.getId()).isNotNull();

    }

    @Test
    void testSavedBeer() {
        Beer savedBeer = beerRepository.save(Beer.builder()
                .beerName("Test beer")
                .beerStyle(BeerStyle.ROAR)
                .upc("AAA")
                .price(new BigDecimal(1))
                .build());

        beerRepository.flush();

        assertThat(savedBeer).isNotNull();
        assertThat(savedBeer.getId()).isNotNull();

    }

}