package com.example.course.services;

import com.example.course.domain.Beer;
import com.example.course.repositories.BeerRepository;
import com.example.course.web.controller.NotFoundException;
import com.example.course.web.mappers.BeerMapper;
import com.example.course.web.mappers.DateMapper;
import com.example.course.web.model.BeerDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Validated
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDto getBeerById(UUID beerId) {
        return beerMapper.beerToBeerDto(beerRepository.findById(beerId)
                        .orElseThrow(NotFoundException::new));
    }

    @Override
    public BeerDto saveNewBeer(@Valid BeerDto beerDto) {

        return beerMapper.beerToBeerDto(
                beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeerById(UUID beerId, @Valid BeerDto beerDto) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setUpc(beerDto.getUpc());
        beer.setPrice(beerDto.getPrice());

        Beer newBeer = beerRepository.save(beer);

        return beerMapper.beerToBeerDto(newBeer);

    }
}
