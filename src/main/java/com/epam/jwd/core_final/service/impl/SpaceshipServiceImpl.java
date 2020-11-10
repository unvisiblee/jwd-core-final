package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.service.SpaceshipService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum SpaceshipServiceImpl implements SpaceshipService {
    INSTANCE;

    private Long id = 1L;
    private SpaceshipFactory factory = new SpaceshipFactory();
    @Override
    public List<Spaceship> findAllSpaceships() {
        return List.copyOf(NassaContext.INSTANCE.retrieveBaseEntityList(Spaceship.class));

    }

    @Override
    public List<Spaceship> findAllSpaceshipsByCriteria(Criteria<? extends Spaceship> criteria) {
        return NassaContext.INSTANCE.retrieveBaseEntityList(Spaceship.class).stream().filter(spaceship -> {
            SpaceshipCriteria shipCriteria = (SpaceshipCriteria) criteria;
            boolean result = true;
            if (shipCriteria.getId() != null)
                result = result && (shipCriteria.getId().equals(spaceship.getId()));
            if (shipCriteria.getName() != null)
                result = result && (shipCriteria.getName().equals(spaceship.getName()));
            if (shipCriteria.getFlightDistance() != null)
                result = result && (shipCriteria.getFlightDistance().equals(spaceship.getFlightDistance()));
            if (shipCriteria.getCrew() != null)
                result = result && (shipCriteria.getCrew().equals(spaceship.getCrew()));
            return result;
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<Spaceship> findSpaceshipByCriteria(Criteria<? extends Spaceship> criteria) {
        return findAllSpaceshipsByCriteria(criteria).stream().findFirst();
    }

    @Override
    public Spaceship updateSpaceshipDetails(Spaceship spaceship) {
        return null;
    }

    @Override
    public void assignSpaceshipOnMission(Spaceship spaceship) throws RuntimeException {

    }

    @Override
    public Spaceship createSpaceship(Object ...args) throws RuntimeException {
        Spaceship newSpaceship = factory.create(args[0], args[1], args[2]);
        return NassaContext.INSTANCE.retrieveBaseEntityList(Spaceship.class).stream()
                .filter((spaceship) -> {
                    if (newSpaceship.getName().equals(spaceship.getName())) {
                        System.out.println("WORKKKK");
                        return true;
                    }
                    return false;
                    })
                .findFirst()
                .orElseGet(() -> setIdAndAddToCollection(newSpaceship));
    }

    private Spaceship setIdAndAddToCollection(Spaceship spaceship) {
        spaceship.setId(++id);
        NassaContext.INSTANCE.retrieveBaseEntityList(Spaceship.class).add(spaceship);
        return spaceship;
    }
}
