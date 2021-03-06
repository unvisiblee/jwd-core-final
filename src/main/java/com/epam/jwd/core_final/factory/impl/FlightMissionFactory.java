package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.exception.IllegalFlightMissionCreateArgsException;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FlightMissionFactory implements EntityFactory<FlightMission> {
    @Override
    public FlightMission create(Object... args) throws IllegalFlightMissionCreateArgsException {
        // String name, LocalDate startDate, LocalDate endDate, Long distance
        if (args.length != 4)
            throw new IllegalFlightMissionCreateArgsException(args);

        return new FlightMission((String) args[0], (LocalDateTime) args[1], (LocalDateTime) args[2], (Long) args[3]);
    }
}
