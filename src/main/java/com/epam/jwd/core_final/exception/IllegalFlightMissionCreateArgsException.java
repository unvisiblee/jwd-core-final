package com.epam.jwd.core_final.exception;

import java.util.Arrays;

public class IllegalFlightMissionCreateArgsException extends  IllegalCreateArgsException {
    public IllegalFlightMissionCreateArgsException(Object[] passedArgs) {

        super(passedArgs);
    }

    @Override
    public String getMessage() {
        return "You've passed " + passedArgs.length + "arguments: " + Arrays.toString(passedArgs) +
                "\n Required arguments(4): String name, LocalDate startDate, LocalDate endDate, Long distance.";
    }
}
