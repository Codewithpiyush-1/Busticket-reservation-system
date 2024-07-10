package com.reservationapp.Controller;

import com.reservationapp.Entity.Bus;
import com.reservationapp.Entity.Route;
import com.reservationapp.Entity.SubRoute;
import com.reservationapp.Payload.BusDto;
import com.reservationapp.Payload.SearchListOfBusesDto;
import com.reservationapp.Repository.BusRepository;
import com.reservationapp.Repository.RouteRepository;
import com.reservationapp.Repository.SubRouteRepository;
import com.reservationapp.Service.BusService;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bus")
public class BusController {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private BusRepository busRepository;
    @Autowired
    private BusService busService;

    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private SubRouteRepository subRouteRepository;

    @PostMapping("/add")
    public ResponseEntity<Bus> addBus(@RequestBody BusDto busDto) throws ParseException {
        Bus bus = busService.addBus(busDto);
        return new ResponseEntity<>(bus, HttpStatus.CREATED);

    }

    @GetMapping
    public List<SearchListOfBusesDto> getAllBuses(@RequestParam String fromLocation,
                                                  @RequestParam String toLocation,
                                                  @RequestParam String fromDate) {
        List<Route> routes = routeRepository.findByFromLocationAndToLocationAndFromDateToDate(fromLocation, toLocation, fromDate);
        List<SubRoute> subRoutes = subRouteRepository.findByFromLocationAndToLocationAndFromDateToDate(fromLocation, toLocation, fromDate);


        List<SearchListOfBusesDto> buses = new ArrayList<>();
        if (routes != null) {
            for (Route route : routes) {
                Bus bus = busRepository.findById(route.getBusId()).get();
                SearchListOfBusesDto searchListOfBusesDto = mapToSearchListOfBusesDto(bus, route);
                buses.add(searchListOfBusesDto);
            }

        }
        if (subRoutes != null) {
            for (SubRoute route : subRoutes) {
                Bus bus = busRepository.findById(route.getBusId()).get();
                SearchListOfBusesDto searchListOfBusesDto = mapToSearchListOfBusesDto(bus, route);
                buses.add(searchListOfBusesDto);
            }
            return buses;
        }

        return null;


    }

    SearchListOfBusesDto mapToSearchListOfBusesDto(Bus bus, Route route) {
        SearchListOfBusesDto searchListOfBusesDto = new SearchListOfBusesDto();
        searchListOfBusesDto.setBusid(bus.getBusId());
        searchListOfBusesDto.setBusNumber(bus.getBusNumber());
        searchListOfBusesDto.setPrice(bus.getPrice());
        searchListOfBusesDto.setBusType(bus.getBusType());
        searchListOfBusesDto.setTotalSeats(bus.getTotalSeats());
        searchListOfBusesDto.setAvailableSeats(bus.getAvailableSeats());
        searchListOfBusesDto.setFromLocation(route.getFromLocation());
        searchListOfBusesDto.setToLocation(route.getToLocation());
        searchListOfBusesDto.setFromDate(route.getFromDate());
        searchListOfBusesDto.setToDate(route.getToDate());
        searchListOfBusesDto.setToTime(route.getToTime());
        searchListOfBusesDto.setFromTime(route.getFromTime());
        searchListOfBusesDto.setRouteId(route.getId());
        searchListOfBusesDto.setTotalDuration(route.getTotalDuration());
        return searchListOfBusesDto;

    }

    SearchListOfBusesDto mapToSearchListOfBusesDto(Bus bus, SubRoute route) {
        SearchListOfBusesDto searchListOfBusesDto = new SearchListOfBusesDto();
        searchListOfBusesDto.setBusid(bus.getBusId());
        searchListOfBusesDto.setBusNumber(bus.getBusNumber());
        searchListOfBusesDto.setPrice(bus.getPrice());
        searchListOfBusesDto.setBusType(bus.getBusType());
        searchListOfBusesDto.setTotalSeats(bus.getTotalSeats());
        searchListOfBusesDto.setAvailableSeats(bus.getAvailableSeats());
        searchListOfBusesDto.setFromLocation(route.getFromLocation());
        searchListOfBusesDto.setToLocation(route.getToLocation());
        searchListOfBusesDto.setFromDate(route.getFromDate());
        searchListOfBusesDto.setToDate(route.getToDate());
        searchListOfBusesDto.setToTime(route.getToTime());
        searchListOfBusesDto.setFromTime(route.getFromTime());
        searchListOfBusesDto.setRouteId(route.getId());
        searchListOfBusesDto.setTotalDuration(route.getTotalDuration());
        return searchListOfBusesDto;
    }


}



















