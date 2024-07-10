package com.reservationapp.Controller;

import com.reservationapp.Entity.Bus;
import com.reservationapp.Entity.Passenger;
import com.reservationapp.Entity.Route;
import com.reservationapp.Entity.SubRoute;
import com.reservationapp.Exception.ResourceNotFound;
import com.reservationapp.Payload.ReservationDto;
import com.reservationapp.Repository.BusRepository;
import com.reservationapp.Repository.PassengerRepository;
import com.reservationapp.Repository.RouteRepository;
import com.reservationapp.Repository.SubRouteRepository;
import com.reservationapp.util.EmailService;
import com.reservationapp.util.ExcelGeneratorService;
import com.reservationapp.util.PdfTicketGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private ExcelGeneratorService excelGeneratorService;
    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private SubRouteRepository subRouteRepository;

    @Autowired
    private BusRepository busRepository;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private PdfTicketGeneratorService pdfTicketGeneratorService;

    //http://localhost:8080/api/reservation?busId=1&roouteId=2
@PostMapping
    public ResponseEntity<String> bookTicket(@RequestParam long busId,
                                     @RequestParam long routeId,

                                     @RequestBody  Passenger passenger){

        boolean busIsPresent=false;
        boolean routeIsPresent=false;
        boolean subRouteIsPresent=false;
    Optional<Bus> byId= busRepository.findById(busId);
    if(byId.isPresent()) {
        busIsPresent = true;
        Bus bus = byId.get();
    }
        Optional<Route> byRouteId = routeRepository.findById(routeId);
        if(byRouteId.isPresent()){
            routeIsPresent=true;
            Bus bus= byId.get();
    }
        Optional<SubRoute> bySubRouteId=subRouteRepository.findById(routeId);
        if(byRouteId.isPresent()){
            subRouteIsPresent=true;
            Bus bus=byId.get();
        }

        if (busIsPresent&&routeIsPresent||busIsPresent&&subRouteIsPresent){
            Passenger p = new Passenger();
            p.setFirstName(passenger.getFirstName());
            p.setLastName(passenger.getLastName());
            p.setEmail(passenger.getEmail());
            p.setMobile(passenger.getMobile());
            p.setRouteId(routeId);
            p.setBusId(busId);
            Passenger savedPassenger = passengerRepository.save(p);
            byte[] pdfbytes = pdfTicketGeneratorService.generateTicket(savedPassenger, byRouteId.get().getFromLocation(), byRouteId.get().getToLocation(), byRouteId.get().getFromDate(), byRouteId.get().getToDate());
            emailService.sendEmailWithAttachement(passenger.getEmail(),"Booking Confirmed","your reservation id"+savedPassenger.getId(),pdfbytes,"ticket");

        }
        return new  ResponseEntity<>("done", HttpStatus.CREATED);

    }
    @GetMapping("/passengers/excel")
    public ResponseEntity<byte[]> downloadPassengerExcel() {
        try {
            // Assuming you have a service to retrieve passengers from a database or another source
            List<Passenger> passengers = fetchPassengersFromDataBase();

            byte[] excelBytes = excelGeneratorService.generateExcel(passengers);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("filename", "passenger_data.xlsx");

            return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Dummy method, replace it with actual logic to fetch passengers
    private List<Passenger> fetchPassengersFromDataBase() {

        return passengerRepository.findAll();
    }
}

