package com.example.course.services.inventory;

import com.example.course.services.inventory.model.BeerInventoryDto;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by jt on 2019-06-07.
 */
@Slf4j
//@ConfigurationProperties(prefix = "sfg.brewery", ignoreUnknownFields = false)
@Service
public class BeerInventoryServiceRestTemplateImpl implements BeerInventoryService {

    private final String INVENTORY_PATH = "/api/v1/beer/{beerId}/inventory";
    private final RestTemplate restTemplate; //per fare chimate HTTP ad altro microservizio
    @Value("${sfg.brewery.beer-inventory-service-host}")
    @Setter
    private String beerInventoryServiceHost; // = http://localhost:8082
    //lo abbiamo commentato dato che abbiamo messo @Setter
//    public void setBeerInventoryServiceHost(String beerInventoryServiceHost) {
//        this.beerInventoryServiceHost = beerInventoryServiceHost;
//    }

    public BeerInventoryServiceRestTemplateImpl(RestTemplateBuilder restTemplateBuilder, @Value("${sfg.brewery.beer-inventory-service-host}") String beerInventoryServiceHost) {
        this.restTemplate = restTemplateBuilder.build();
        this.beerInventoryServiceHost = beerInventoryServiceHost;
    }

    @Override
    public Integer getOnhandInventory(UUID beerId) {

        log.debug("Calling Inventory Service");

        //chiamata http get
        ResponseEntity<List<BeerInventoryDto>> responseEntity = restTemplate.exchange(
                beerInventoryServiceHost + INVENTORY_PATH, // URL + /api/v1/beer/{beerId}/inventory
                HttpMethod.GET, //tipo di chiamata HTTP
                null, //eventuale body della richiesta (non no lo abbiamo)
                new ParameterizedTypeReference<List<BeerInventoryDto>>(){}, //tipo di dato che ci aspettiamo di ricevere
                (Object) beerId); //parametro da inserire nel beerId

        //Calcolo quantita' totale
        Integer onHand = Objects.requireNonNull(responseEntity.getBody()) //controlla che risp. http non sia null
                .stream()
                .mapToInt(BeerInventoryDto::getQuantityOnHand)//di ogni oggetto beer chiede la quantity
                .sum();//somma tutte quantity

        return onHand;
    }
}