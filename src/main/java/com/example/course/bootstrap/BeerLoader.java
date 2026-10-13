package com.example.course.bootstrap;

import com.example.course.domain.Beer;
import com.example.course.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }

    private void loadBeerObjects(){
        if(beerRepository.count() == 0){
            beerRepository.save(Beer.builder()
                    .beerName("Mangone")
                    .beerStyle("IPA")
                    .upc(10L)
                    .price(BigDecimal.valueOf(12.95))
                    .minOnHand(12)
                    .quantityToBrew(200)
                    .build());


            beerRepository.save(Beer.builder()
                    .beerName("Galdeon")
                    .beerStyle("GOSE")
                    .upc(15L)
                    .price(BigDecimal.valueOf(16.95))
                    .minOnHand(12)
                    .quantityToBrew(200)
                    .build());
        }


        System.out.println("-----------------Num birre: " + beerRepository.count() + " ----------------------------");


    }
}
