package com.reservationapp.Payload;

import com.reservationapp.Entity.Driver;
import com.reservationapp.Entity.SubRoute;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusDto {
 private String busNumber;
 private String busType;
 private Double price;
 private String totalSeat;
 private String availableSeats;
 private RouteDto route;
 private List<SubRouteDto>subRoutes;

    }

