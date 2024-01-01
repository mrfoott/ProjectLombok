package guru.springframework.spring6restmvc.model.servies;

import guru.springframework.spring6restmvc.model.Beer;
import guru.springframework.spring6restmvc.model.BeerStyle;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class BeerServiceImpl implements BeerService {
    @Override
    public Beer getBeerById(UUID id) {
        return Beer.builder()
                .id(id)
                .version(1)
                .beerName("Tiger")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("123456")
                .price(new BigDecimal("25000"))
                .quantityOnHand(100)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

    }
}
