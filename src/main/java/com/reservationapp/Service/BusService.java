package com.reservationapp.Service;

import com.reservationapp.Entity.Bus;
import com.reservationapp.Payload.BusDto;

import com.reservationapp.Repository.BusRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BusService {
    @Autowired
    private BusRepository busRepository;

    @Transactional
    public Bus addBus(BusDto busDto) {


        Bus bus = new Bus();
        bus.setBusNumber(busDto.getBusNumber());
        bus.setBusType(busDto.getBusType());
        bus.setPrice(busDto.getPrice());
        bus.setTotalSeats(bus.getTotalSeats());
        bus.setAvailableSeats(bus.getAvailableSeats());
        Bus savedBus = busRepository.save(bus);
        return null;
    }
}






































