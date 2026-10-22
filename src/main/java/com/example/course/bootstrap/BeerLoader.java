package com.example.course.bootstrap;

import com.example.course.domain.Beer;
import com.example.course.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

//@Component
public class BeerLoader implements CommandLineRunner {


    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";
    public static final UUID BEER_1_UUID = UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb");
    public static final UUID BEER_2_UUID = UUID.fromString("a712d914-61ea-4623-8bd0-32c0f6545bfd");
    public static final UUID BEER_3_UUID = UUID.fromString("026cc3c8-3a0c-4083-a05b-e908048c1b08");

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
                    .upc(BEER_1_UPC)
                    .price(BigDecimal.valueOf(12.95))
                    .minOnHand(12)
                    .quantityToBrew(200)
                    .build());


            beerRepository.save(Beer.builder()
                    .beerName("Galdeon")
                    .beerStyle("GOSE")
                    .upc(BEER_2_UPC)
                    .price(BigDecimal.valueOf(16.95))
                    .minOnHand(12)
                    .quantityToBrew(200)
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("astreus")
                    .beerStyle("GOSE")
                    .upc(BEER_3_UPC)
                    .price(BigDecimal.valueOf(23.00))
                    .minOnHand(16)
                    .quantityToBrew(12)
                    .build());
        }


        System.out.println("-----------------Num birre: " + beerRepository.count() + " ----------------------------");


    }
}
