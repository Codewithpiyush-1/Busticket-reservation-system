package com.reservationapp.Service;

import com.reservationapp.Entity.Bus;
import com.reservationapp.Entity.Route;
import com.reservationapp.Exception.ResourceNotFound;
import com.reservationapp.Repository.BusRepository;
import com.reservationapp.Repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;

import java.util.Optional;

public class RouteService {
    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private BusRepository busRepository;

    public Route createRoute(long BudId, Route route) {
        Bus bus = busRepository.findById(BudId).orElseThrow(

                () -> new ResourceNotFound("Bus Not Added")
        );
        Route r =routeRepository.findByBusId(route.getBusId());
        if(r!=null){
            throw new ResourceNotFound("Route was already added");
        }

        if (r==null){
            routeRepository.save(route);
            return route;
        }
        return null;
    }

}