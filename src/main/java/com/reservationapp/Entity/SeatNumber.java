package com.reservationapp.Entity;

import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class SeatNumber {
    private long id;
    private String seatNumber;
    private long busId;
    private boolean seatStatus;
}
