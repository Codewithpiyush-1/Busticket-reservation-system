package com.reservationapp.Entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="User_registrations")
public class UserRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private  String name;
    private String email;
    private String password;
//@Lob this annotation is used for image uploading

    //TINYBLOB:
//Maximum length: 255 bytes
//BLOB:
//
//Maximum length: 65,535 bytes (64 KB)
//MEDIUMBLOB:
//
//Maximum length: 16,777,215 bytes (16 MB)
//LONGBLOB:
//
//Maximum length: 4,294,967,295 bytes (4 GB)
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] profilePicture;
}
