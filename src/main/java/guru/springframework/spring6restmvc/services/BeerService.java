package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.models.BeerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeerService {

    List<BeerDTO> listBeers(String beerName);

    Optional<BeerDTO> getBeerById(UUID id);

    BeerDTO saveNewBeer(BeerDTO beerDTO);

    Optional<BeerDTO> updateBeerById(UUID id, BeerDTO beerDTO);

    Boolean deleteById(UUID beerId);

    void patchBeerById(UUID beerId, BeerDTO beerDTO);
    
}
