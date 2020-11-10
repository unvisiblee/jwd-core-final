package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.strategy.ReadStrategy;
import com.epam.jwd.core_final.strategy.impl.ReadCrewStrategy;
import com.epam.jwd.core_final.strategy.impl.ReadSpaceshipsStrategy;
import com.epam.jwd.core_final.util.LoggerImpl;
import com.epam.jwd.core_final.util.PropertyReaderUtil;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;

import static javafx.application.Platform.exit;

// todo
public class NassaContext implements ApplicationContext {

    public static final NassaContext INSTANCE = new NassaContext();
    // no getters/setters for them
    private final Collection<CrewMember> crewMembers = new ArrayList<>();
    private final Collection<Spaceship> spaceships = new ArrayList<>();
    private final Collection<FlightMission> flightMissions = new ArrayList<>();

    private ReadStrategy readCrewStrategy = new ReadCrewStrategy();
    private ReadStrategy readShipsStrategy = new ReadSpaceshipsStrategy();
    private ApplicationProperties appProperties = PropertyReaderUtil.getApplicationProperties();



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
        try {
            readCrewStrategy.read(appProperties.getCrewFileName());
            readShipsStrategy.read(appProperties.getSpaceshipsFileName());
        } catch (FileNotFoundException ex) {
            LoggerImpl.INSTANCE.logger.error(ex.getMessage());
            exit();
        }
    }

    public ApplicationProperties getAppProperties() {
        return appProperties;
    }
}
