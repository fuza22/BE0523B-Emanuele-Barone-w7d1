package it.epicode.w7d1.exception;

public class AlreadyAssignedException extends RuntimeException{

    public AlreadyAssignedException(String message) {
        super(message);
    }
}
