package com.reservationapp.Exception;

public class ResourceNotFound extends RuntimeException{
    public ResourceNotFound(String s){
        super(s);
    }
}
