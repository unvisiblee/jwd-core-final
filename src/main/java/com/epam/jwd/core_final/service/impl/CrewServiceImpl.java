package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.exception.CrewMemberIsNotReadyForMissionException;
import com.epam.jwd.core_final.exception.IllegalCreateArgsException;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.util.LoggerImpl;

import java.util.*;
import java.util.stream.Collectors;

public enum CrewServiceImpl implements CrewService {
    INSTANCE;

    private Long id = 0L;
    private final CrewMemberFactory crewFactory = new CrewMemberFactory();

    @Override
    public List<CrewMember> findAllCrewMembers() {
        return List.copyOf(NassaContext.INSTANCE.retrieveBaseEntityList(CrewMember.class));
    }

    @Override
    public List<CrewMember> findAllCrewMembersByCriteria(Criteria<? extends CrewMember> criteria) {
        CrewMemberCriteria memberCriteria = (CrewMemberCriteria) criteria;
        return NassaContext.INSTANCE.retrieveBaseEntityList(CrewMember.class).stream().filter(crewMember -> {
            boolean result = true;
            if (memberCriteria.getId() != null)
                result = (memberCriteria.getId().equals(crewMember.getId()));
            if (memberCriteria.getName() != null)
                result = result && (memberCriteria.getName().equals(crewMember.getName()));
            if (memberCriteria.getRank() != null)
                result = result && (memberCriteria.getRank().equals(crewMember.getRank()));
            if (memberCriteria.getRole() != null)
                result = result && (memberCriteria.getRole().equals(crewMember.getRole()));
            return result;
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<CrewMember> findCrewMemberByCriteria(Criteria<? extends CrewMember> criteria) {
        return findAllCrewMembersByCriteria(criteria).stream().findFirst();
    }

    @Override
    public CrewMember updateCrewMemberDetails(CrewMember crewMember) {
        Optional<CrewMember> member = NassaContext.INSTANCE.retrieveBaseEntityList(CrewMember.class).stream()
                .filter(crewMember1 -> crewMember.getName().equals(crewMember1.getName()))
                .findFirst();
        if (!member.isEmpty()) {
            NassaContext.INSTANCE.retrieveBaseEntityList(CrewMember.class).remove(member.get());
            NassaContext.INSTANCE.retrieveBaseEntityList(CrewMember.class).add(crewMember);
            return crewMember;
        } else return null;
    }

    @Override
    public void assignCrewMemberOnMission(CrewMember crewMember, FlightMission mission) throws RuntimeException {
        if (crewMember.getReadyForNextMissions())
            mission.getAssignedCrew().add(crewMember);
        else throw new CrewMemberIsNotReadyForMissionException();
    }


    @Override
    public CrewMember createCrewMember(Object... args) throws IllegalCreateArgsException {
        CrewMember newMember = crewFactory.create(args[0], args[1], args[2]);
        return NassaContext
                .INSTANCE.retrieveBaseEntityList(CrewMember.class).stream()
                    .filter(member -> newMember.getName().equals(member.getName()))
                    .findFirst()
                    .orElseGet(() -> setIdAndAddToCollection(newMember));
    }

    private CrewMember setIdAndAddToCollection(CrewMember crewMember) {
        crewMember.setId(++id);
        NassaContext.INSTANCE.retrieveBaseEntityList(CrewMember.class).add(crewMember);
        LoggerImpl.INSTANCE.logger.info("Created Crew Member with id: " + (id + 1));

        return crewMember;
    }
}
