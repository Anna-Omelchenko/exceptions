package ru.netology.shop.exception;

public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(String s) {
        super(s);
    }
}
