package com.example.course.services;

import com.example.course.web.model.BeerDto;
import com.example.course.web.model.BeerPagedList;
import com.example.course.web.model.BeerStyleEnum;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {
    BeerDto getBeerById(UUID beerId, boolean showInventoryOnHand);

    BeerDto saveNewBeer(@Valid BeerDto beerDto);

    BeerDto updateBeerById(UUID beerId, @Valid BeerDto beerDto);

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand);
}
