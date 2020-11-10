package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationSubMenu;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;
import com.epam.jwd.core_final.util.ConsoleColors;

import java.time.LocalDateTime;
import java.util.*;

public class AddNewMissionSubMenu implements ApplicationSubMenu {

    public static void addMission() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        ViewEntitiesSubMenu.viewAllSpaceships();


        System.out.println(ConsoleColors.GREEN_UNDERLINED + "Choose a Spaceship: enter spaceship's name to assign it on mission" + ConsoleColors.RESET);

        String spaceShipName = null;
        Optional<Spaceship> spaceship;
        SpaceshipCriteria.SpaceshipCriteriaBuilder spaceShipCriteriaBuilder = new SpaceshipCriteria.SpaceshipCriteriaBuilder();

        while (true) {
            spaceShipName = scanner.next();
            spaceship = SpaceshipServiceImpl.INSTANCE.findSpaceshipByCriteria(spaceShipCriteriaBuilder.name(spaceShipName).build());
            if (spaceship.isEmpty() || !spaceship.get().isReadyForNextMissions()) {
                System.out.println(ConsoleColors.RED + "You've entered the wrong spaceship name, try again" + ConsoleColors.RESET);
            } else break;
        }

        System.out.println(ConsoleColors.GREEN_UNDERLINED + "Please, enter mission's name: " + ConsoleColors.RESET);
        String missionName = scanner.next();

        List<CrewMember> crewList = new ArrayList<>();
        Map<Role, Short> crew =  spaceship.get().getCrew();
        CrewMemberCriteria.CrewMemberCriteriaBuilder memberCriteriaBuilder = new CrewMemberCriteria.CrewMemberCriteriaBuilder();

        for (Map.Entry<Role, Short> entry: crew.entrySet()) { // assigning crew for mission
            CrewMemberCriteria criteria = (CrewMemberCriteria) memberCriteriaBuilder
                                .role(Role.resolveRoleById(entry.getKey().getId().intValue()))
                                .isReadyForNextMissions(true)
                                .build();
            List<CrewMember> freeCrewMembers = CrewServiceImpl.INSTANCE.findAllCrewMembersByCriteria(criteria);
            for (int i = 0; i < entry.getValue(); i++) {
                crewList.add(freeCrewMembers.get(i));
                freeCrewMembers.get(i).setReadyForNextMissions(false);
                CrewServiceImpl.INSTANCE.updateCrewMemberDetails(freeCrewMembers.get(i));
            }
        }

        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusSeconds(spaceship.get().getFlightDistance() / 3500);

        FlightMission mission = MissionServiceImpl.INSTANCE.createMission(missionName, startDate, endDate, spaceship.get().getFlightDistance());
        mission.setAssignedCrew(crewList);
        spaceship.get().setReadyForNextMissions(false);
        SpaceshipServiceImpl.INSTANCE.updateSpaceshipDetails(spaceship.get());
        mission.setAssignedSpaceShip(spaceship.get());
        MissionServiceImpl.INSTANCE.updateMissionDetails(mission);
    }
}