package com.reservationapp.Entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String LicenseNumber;
    private String AddNumbers;
    private String Address;
    private String ContactNumber;
    private String AlternateContactNumber;
    private String emailId;

}



