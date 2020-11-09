package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;

import java.util.ArrayList;
import java.util.Collection;

// todo
public class NassaContext implements ApplicationContext {
    public static final NassaContext INSTANCE = new NassaContext();
    // no getters/setters for them
    private final Collection<CrewMember> crewMembers = new ArrayList<>();
    private final Collection<Spaceship> spaceships = new ArrayList<>();
    private final Collection<FlightMission> flightMissions = new ArrayList<>();

    @Override
    public <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass) {
        if (tClass.equals(CrewMember.class))
            return (Collection<T>) this.crewMembers;
        if (tClass.equals(Spaceship.class))
            return (Collection<T>) this.spaceships;
        if (tClass.equals(FlightMission.class))
            return (Collection<T>) this.flightMissions;

        return null;
    }

    /**
     * You have to read input files, populate collections
     * @throws InvalidStateException
     */
    @Override
    public void init() throws InvalidStateException {
        throw new InvalidStateException();
    }
}
