package com.reservationapp.Repository;

import com.reservationapp.Entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route,Long> {


    Route findByBusId(long busId);

    List<Route> findByFromLocationAndToLocationAndFromDateToDate(String fromLocation,String FromLocation,String fromDate);
}
