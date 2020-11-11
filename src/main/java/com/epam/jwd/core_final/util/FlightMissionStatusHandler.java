package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public enum FlightMissionStatusHandler {
    INSTANCE;

    public static void handle() {
        List<FlightMission> missionList = MissionServiceImpl.INSTANCE.findAllMissions();

        for (FlightMission mission: missionList) {
            if (!mission.getMissionResult().equals(MissionResult.COMPLETED) && mission.getEndDate().isBefore(LocalDateTime.now())) {
                mission.setMissionResult(MissionResult.COMPLETED);
                releaseAllCrewMembers(mission);
            }

            if (mission.getMissionResult().equals(MissionResult.PLANNED)) {
                mission.setMissionResult(MissionResult.IN_PROGRESS);
            } else if (mission.getMissionResult().equals(MissionResult.IN_PROGRESS)) {
                Random random = new Random();
                if (random.nextInt(99) < 10) {
                    mission.setMissionResult(MissionResult.FAILED);
                }
            }
        }
    }

    private static void releaseAllCrewMembers(FlightMission mission) {
        List <CrewMember> crewMemberList = mission.getAssignedCrew();
        mission.getAssignedSpaceShip().setReadyForNextMissions(true);
        SpaceshipServiceImpl.INSTANCE.updateSpaceshipDetails(mission.getAssignedSpaceShip());

        for (CrewMember crewMember: crewMemberList) {
            crewMember.setReadyForNextMissions(true);
            CrewServiceImpl.INSTANCE.updateCrewMemberDetails(crewMember);
        }
    }
}