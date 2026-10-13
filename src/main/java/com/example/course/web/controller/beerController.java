package com.example.course.web.controller;

import com.example.course.web.model.BeerDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/beer")
class BeerController {


    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId) {
        //todo: real impl
        return new ResponseEntity<>(BeerDto.builder().id(beerId).build(), HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<Void> saveNewBeer(@RequestBody @Valid BeerDto beerDto) {
        //todo: impl
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBeerBfyId(@PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDto beerDto) {


    }
}
