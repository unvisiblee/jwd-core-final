package com.epam.jwd.core_final.exception;


public class InvalidStateException extends Exception {

    private String filepath;

    public InvalidStateException(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public String getMessage() {
        return "Error in your filepath, check the correctness: " + filepath;
    }
}
