package ru.shopper.exception;

public class PositionNotFoundException extends Exception {
    public PositionNotFoundException() {}

    public PositionNotFoundException(String message) {
        super(message);
    }
}
