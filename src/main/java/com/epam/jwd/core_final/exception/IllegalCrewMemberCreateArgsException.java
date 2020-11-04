package com.epam.jwd.core_final.exception;

import java.util.Arrays;

public class IllegalCrewMemberCreateArgsException extends IllegalCreateArgsException {

    public IllegalCrewMemberCreateArgsException(Object[] passedArgs) {
        super(passedArgs);
    }

    @Override
    public String getMessage() {
        return "You've passed " + passedArgs.length + "arguments: " + Arrays.toString(passedArgs) +
                "\n Required arguments(3): String name, Role role, Rank rank.";
    }
}
