package com.reservationapp.Payload;



public class UserRegistrationDto {

    private long id;
    private  String name;
    private String email;
    private String password;
    //@Lob this annotation is used for image uploading

    private byte[] profilePicture;
}
