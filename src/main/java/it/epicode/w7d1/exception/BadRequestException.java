package it.epicode.w7d1.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message){
        super(message);
    }
}
