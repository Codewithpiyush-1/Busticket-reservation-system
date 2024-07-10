package com.reservationapp.Entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long busId;
    private String busNumber;
    private String busType;

    private Double price;

    private int totalSeats;
    private int availableSeats;



    @OneToOne(mappedBy="bus")
    private Route route;


}
