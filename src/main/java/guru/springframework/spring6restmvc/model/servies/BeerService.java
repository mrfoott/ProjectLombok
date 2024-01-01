package guru.springframework.spring6restmvc.model.servies;

import guru.springframework.spring6restmvc.model.Beer;

import java.util.UUID;

public interface BeerService {

    Beer getBeerById(UUID id);

}
