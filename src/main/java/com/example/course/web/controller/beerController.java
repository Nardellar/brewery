package com.example.course.web.controller;

import com.example.course.services.BeerService;
import com.example.course.web.model.BeerDto;
import com.example.course.web.model.BeerPagedList;
import com.example.course.web.model.BeerStyleEnum;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor//genera costruttore e autowirde per tutti i campi final ---> infatti non scriviamo new per il beerService
@RestController
@RequestMapping("/api/v1/")
class BeerController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;
    private static final Boolean DEFAULT_SHOW_INVENTORY_ON_HAND = false;

    private final BeerService beerService;

    @GetMapping(produces = "application/json", path = "beer")
    public ResponseEntity<BeerPagedList> listBeers(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                   @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                   @RequestParam(value = "beerName", required = false) String beerName,
                                                   @RequestParam(value = "beerStyle", required = false)BeerStyleEnum beerStyle,
                                                   @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand)
    {
        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        if (showInventoryOnHand == null) {
            showInventoryOnHand = DEFAULT_SHOW_INVENTORY_ON_HAND;
        }

        BeerPagedList beerList = beerService.listBeers(beerName, beerStyle, PageRequest.of(pageNumber, pageSize), showInventoryOnHand);

        return new ResponseEntity<BeerPagedList>(beerList, HttpStatus.OK);
    }

    @GetMapping("beer/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId, @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand) {
        if (showInventoryOnHand == null) {
            showInventoryOnHand = DEFAULT_SHOW_INVENTORY_ON_HAND;
        }
        return new ResponseEntity<BeerDto>(beerService.getBeerById(beerId, showInventoryOnHand), HttpStatus.OK);
    }

    @GetMapping("beerUpc/{beerUpc}")
    public ResponseEntity<BeerDto> getBeerByUpc(@PathVariable("beerUpc") String beerUpc){
        return new ResponseEntity<BeerDto>(beerService.getBeerByUpc(beerUpc), HttpStatus.OK);
    }


    @PostMapping(path = "beer")
    public ResponseEntity<BeerDto> saveNewBeer(@RequestBody @Valid BeerDto beerDto) {
        //todo: impl
        return new ResponseEntity<>(beerService.saveNewBeer(beerDto), HttpStatus.CREATED);
    }

    @PutMapping("beer/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<BeerDto> updateBeerById(@PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDto beerDto) {
        return new ResponseEntity<>(beerService.updateBeerById(beerId, beerDto), HttpStatus.NO_CONTENT);


    }
}
