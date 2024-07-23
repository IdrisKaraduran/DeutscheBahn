package com.deutscheBahn.entity.concreates;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "stations")

public class Station implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String stationName;

    @Column(nullable = false, unique = true)
    private String code;

    // İstasyonun bulunduğu şehir (isteğe bağlı)
    private String city;

    @ManyToMany(mappedBy = "stations")
    private List<Train> trains;


}

