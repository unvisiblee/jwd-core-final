package com.epam.jwd.core_final.exception;

public class IllegalCreateArgsException extends RuntimeException {
    protected final Object[] passedArgs;

    public IllegalCreateArgsException(Object[] passedArgs) {
        this.passedArgs = passedArgs;
    }
}
