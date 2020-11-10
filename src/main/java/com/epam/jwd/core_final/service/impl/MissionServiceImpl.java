package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.service.MissionService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum   MissionServiceImpl implements MissionService {
    INSTANCE;

    private Long id = 0L;
    private FlightMissionFactory missionFactory = new FlightMissionFactory();

    @Override
    public List<FlightMission> findAllMissions() {
        return List.copyOf(NassaContext.INSTANCE.retrieveBaseEntityList(FlightMission.class));
    }

    @Override
    public List<FlightMission> findAllMissionsByCriteria(Criteria<? extends FlightMission> criteria) {
        return NassaContext.INSTANCE.retrieveBaseEntityList(FlightMission.class).stream().filter(flightMission -> {
            FlightMissionCriteria missionCriteria = (FlightMissionCriteria) criteria;
            boolean result = true;
            if (missionCriteria.getId() != null)
                result = result && (missionCriteria.getId().equals(flightMission.getId()));
            if (missionCriteria.getName() != null)
                result = result && (missionCriteria.getName().equals(flightMission.getName()));
            if (missionCriteria.getAssignedCrew() != null)
                result = result && (missionCriteria.getAssignedCrew().equals(flightMission.getAssignedCrew()));
            if (missionCriteria.getAssignedSpaceShip() != null)
                result = result && (missionCriteria.getAssignedSpaceShip().equals(flightMission.getAssignedSpaceShip()));
            if (missionCriteria.getDistance() != null)
                result = result && (missionCriteria.getDistance().equals(flightMission.getDistance()));
            if (missionCriteria.getStartDate() != null)
                result = result && (missionCriteria.getStartDate().equals(flightMission.getStartDate()));
            if (missionCriteria.getEndDate() != null)
                result = result && (missionCriteria.getEndDate().equals(flightMission.getEndDate()));
            return result;
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<FlightMission> findMissionByCriteria(Criteria<? extends FlightMission> criteria) {
        return findAllMissionsByCriteria(criteria).stream().findFirst();

    }

    @Override
    public FlightMission updateMissionDetails(FlightMission flightMission) {
        Optional<FlightMission> mission = NassaContext.INSTANCE.retrieveBaseEntityList(FlightMission.class).stream()
                .filter(flightMission1 -> flightMission.getName().equals(flightMission1.getName()))
                .findFirst();
        if (mission.isPresent()) {
            NassaContext.INSTANCE.retrieveBaseEntityList(FlightMission.class).remove(mission.get());
            NassaContext.INSTANCE.retrieveBaseEntityList(FlightMission.class).add(flightMission);
            return flightMission;
        } else return null;
    }

    @Override
    public FlightMission createMission(Object ...args) {

        FlightMission newMission = missionFactory.create(args[0], args[1], args[2], args[3]);
        return NassaContext
                .INSTANCE.retrieveBaseEntityList(FlightMission.class).stream()
                .filter(mission -> newMission.getName().equals(mission.getName()))
                .findFirst()
                .orElseGet(() -> setIdAndAddToCollection(newMission));
    }

    private FlightMission setIdAndAddToCollection(FlightMission flightMission) {
        flightMission.setId(++id);
        NassaContext.INSTANCE.retrieveBaseEntityList(FlightMission.class).add(flightMission);
        return flightMission;
    }
}
