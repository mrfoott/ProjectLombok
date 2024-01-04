package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.model.Beer;
import guru.springframework.spring6restmvc.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
//@RequestMapping("/api/v1/beer")
public class BeerController {

    public static final String BEER_PATH = "/api/v1/beer";
    public static final String BEER_ID_PATH = BEER_PATH + "/{beerId}";


    private final BeerService beerService;

    @PatchMapping(BEER_ID_PATH)
    public ResponseEntity updateBeerPatchById(@PathVariable("beerId") UUID beerId, @RequestBody Beer beer) {

        beerService.patchBeerById(beerId, beer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping(BEER_ID_PATH)
    public ResponseEntity deleteById(@PathVariable("beerId") UUID beerId) {

        beerService.deleteById(beerId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(BEER_ID_PATH)
    public ResponseEntity updateById(@PathVariable("beerId") UUID beerId, @RequestBody Beer beer) {

        beerService.updateBeerById(beerId, beer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

    @PostMapping(BEER_PATH)
//    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity handlePost(@RequestBody Beer beer) {

        Beer savedBeer = beerService.saveNewBeer(beer);

        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("Con Moe", "Con Moe");
        httpHeaders.add("Location", BEER_PATH + "/" + savedBeer.getId().toString());

        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);

    }

//    @RequestMapping(method = RequestMethod.GET)
    @GetMapping(value = BEER_PATH)
    public List<Beer> listBeers() {
        return beerService.listBeers();
    }

//    @RequestMapping(value = BEER_ID_PATH, method = RequestMethod.GET)
    @GetMapping(value = BEER_ID_PATH)
    public Beer getBeerById(@PathVariable("beerId") UUID beerId) {

        log.debug("Get Beer By Id - in Controller was called!");

        return beerService.getBeerById(beerId).orElseThrow(NotFoundException::new);

    }

//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity handleNotFoundException() {
//        System.out.println("In exception handler!!!");
//        System.out.println("ERROR!!!\n404 NOT FOUND");
//
//        return ResponseEntity.notFound().build();
//    }

}
