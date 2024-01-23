package guru.springframework.spring6restmvc.repositories;

import guru.springframework.spring6restmvc.bootstrap.BootstrapData;
import guru.springframework.spring6restmvc.entities.Beer;
import guru.springframework.spring6restmvc.models.BeerStyle;
import guru.springframework.spring6restmvc.services.BeerCsvServiceImpl;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({BootstrapData.class, BeerCsvServiceImpl.class})
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void getBeerListByBeerName() {

        List<Beer> beerList = beerRepository.findAllByBeerNameIsLikeIgnoreCase("%IPA%");

        assertThat(beerList.size()).isEqualTo(336);

    }

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