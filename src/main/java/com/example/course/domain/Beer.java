package com.example.course.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Beer {

    @Id //indica che la var sotto e' la PK
    @GeneratedValue(strategy = GenerationType.UUID) //"cliente". richiede di usare un gen di nome UUID
    //@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDgenerator")// e' il gen effettvio, il "fornitore"
    @Column(length = 36,columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID id;

    @Version //gestisce chiamate concorrenti dando priorita' solo alla prima
    private Long version;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp lastModifiedDate;
    private String beerName;
    private String beerStyle;

    @Column(unique = true)
    private Long upc;

    private BigDecimal price;

    private Integer minOnHand;
    private Integer quantityToBrew;

}
