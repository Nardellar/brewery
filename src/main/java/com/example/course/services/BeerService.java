package com.example.course.services;

import com.example.course.web.model.BeerDto;
import jakarta.validation.Valid;

import java.util.UUID;

public interface BeerService {
    BeerDto getBeerById(UUID beerId);

    BeerDto saveNewBeer(@Valid BeerDto beerDto);

    BeerDto updateBeerById(UUID beerId, @Valid BeerDto beerDto);
}
