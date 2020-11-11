package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.IllegalFlightMissionCreateArgsException;
import com.epam.jwd.core_final.exception.IllegalSpaceShipCreateArgsException;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.time.LocalDate;
import java.util.HashMap;

public class SpaceshipFactory implements EntityFactory<Spaceship> {
    @Override
    public Spaceship create(Object ...args) {
        // String name, HashMap<Role, Short>, Long distance
        if (args.length != 3)
            throw new IllegalSpaceShipCreateArgsException(args);

        return new Spaceship((String) args[0], (HashMap<Role, Short>) args[1], (Long) args[2]);
    }
}
