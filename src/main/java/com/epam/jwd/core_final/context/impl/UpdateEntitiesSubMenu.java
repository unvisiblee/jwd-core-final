package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationSubMenu;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.util.ConsoleColors;

import java.util.Optional;
import java.util.Scanner;

public class UpdateEntitiesSubMenu implements ApplicationSubMenu {

    public static void updateEntities() {
        Scanner scanner = new Scanner(System.in);
        int option;
        while (true) {
            System.out.println(ConsoleColors.BLACK_BOLD + "1. Update Crew Members \n" +
                    "2. Update Flight Missions \n" +
                    "3. Update Spaceships \n" +
                    "0. Return to the main menu" + ConsoleColors.RESET);

            option = ApplicationSubMenu.getIntFromUser(scanner);
            if (option == 0)
                break;
            handleUserInput(option);
        }

    }

    private static void handleUserInput(int option) {
        switch (option) {
            case 1:
            {
                updateCrewMembers();
                break;
            }
            case 2:
            {
                updateFlightMissions();
                break;
            }
            case 3:
            {
                updateSpaceships();
                break;
            }
        }
    }

    private static void updateCrewMembers() {
        Scanner scanner = new Scanner(System.in);
        ViewEntitiesSubMenu.viewAllCrewMembers();

        System.out.println(ConsoleColors.GREEN_BOLD + "Enter ID of Crew Member you want to update: " + ConsoleColors.RESET);
        int id = ApplicationSubMenu.getIntFromUser(scanner);

        CrewMemberCriteria.CrewMemberCriteriaBuilder criteriaBuilder = new CrewMemberCriteria.CrewMemberCriteriaBuilder();
        criteriaBuilder.id((long) id);

        Optional<CrewMember> crewMember = CrewServiceImpl.INSTANCE.findCrewMemberByCriteria(criteriaBuilder.build());
        if (crewMember.isEmpty()) {
            System.out.println(ConsoleColors.RED + "User with id (" + id + ") not found, please check your input and try again!" + ConsoleColors.RESET);
            return;
        }

        System.out.println("Choose property to update: " +
                "1. Role \n" +
                "2. Rank \n");

        int property = ApplicationSubMenu.getIntFromUser(scanner);

        if (property == 1)
            updateMemberRole(crewMember.get());
        else if (property == 2)
            updateMemberRank(crewMember.get());
    }

    private static void updateMemberRole(CrewMember crewMember) {
        System.out.println(ConsoleColors.GREEN_BOLD + "Choose new Role: " +
                " 1.MISSION_SPECIALIST(1L),\n" +
                " 2.FLIGHT_ENGINEER(2L),\n" +
                " 3.PILOT(3L),\n" +
                " 4.COMMANDER(4L);");

        int newRoleID = ApplicationSubMenu.getRoleOrRankIDFromUser(new Scanner(System.in));

        crewMember.setRole(Role.resolveRoleById(newRoleID));
        CrewServiceImpl.INSTANCE.updateCrewMemberDetails(crewMember);
    }

    private static void updateMemberRank(CrewMember crewMember) {
        System.out.println(ConsoleColors.GREEN + "Choose new Crew Member Rank: \n" +
                " 1. TRAINEE,\n" +
                " 2. SECOND_OFFICER,\n" +
                " 3. FIRST_OFFICER,\n" +
                " 4. CAPTAIN;" + ConsoleColors.RESET);

        int newRankId = ApplicationSubMenu.getRoleOrRankIDFromUser(new Scanner(System.in));

        crewMember.setRank(Rank.resolveRankById(newRankId));
        CrewServiceImpl.INSTANCE.updateCrewMemberDetails(crewMember);
    }

    private static void updateFlightMissions() {
        ViewEntitiesSubMenu.viewAllSpaceships();

        System.out.println(ConsoleColors.GREEN_BOLD + "Enter ID of the mission you want to update: " + ConsoleColors.RESET);
        int missionId = ApplicationSubMenu.getIntFromUser(new Scanner(System.in));

        FlightMissionCriteria.FlightMissionCriteriaBuilder criteriaBuilder = new FlightMissionCriteria.FlightMissionCriteriaBuilder();
        criteriaBuilder.id((long) missionId);

        Optional<FlightMission> flightMission = MissionServiceImpl.INSTANCE.findMissionByCriteria(criteriaBuilder.build());
        if (flightMission.isEmpty()) {
            System.out.println(ConsoleColors.RED + "Mission with ID you've entered does not exist! " + ConsoleColors.RESET);
            return;
        }

        System.out.println("Choose property to update: \n" +
                "1. Mission Result \n" +
                "2. Distance");

        int option = ApplicationSubMenu.getRoleOrRankIDFromUser(new Scanner(System.in));

        if (option == 1)
            UpdateMissionResult(flightMission.get());
        else if (option == 2)
            UpdateMissionDistance(flightMission.get());
    }

    private static void UpdateMissionResult(FlightMission mission) {
        System.out.println(ConsoleColors.GREEN_BOLD + "Choose new Mission status: " +
                "  1.CANCELLED,\n" +
                "  2.FAILED,\n" +
                "  3.PLANNED,\n" +
                "  4.IN_PROGRESS,\n" +
                "  5.COMPLETED" + ConsoleColors.RESET);

        int missionStatusFromUser = ApplicationSubMenu.getIntFromUser(new Scanner(System.in));
        MissionResult newMissionResult = ViewEntitiesSubMenu.getMissionResultByNumber(missionStatusFromUser);
        mission.setMissionResult(newMissionResult);

        MissionServiceImpl.INSTANCE.updateMissionDetails(mission);


    }

    private static void UpdateMissionDistance(FlightMission mission) {
        System.out.println(ConsoleColors.GREEN_BOLD + "Enter new mission Distance: ");
        int newDistance = ApplicationSubMenu.getIntFromUser(new Scanner(System.in));

        mission.setDistance((long) newDistance);
        MissionServiceImpl.INSTANCE.updateMissionDetails(mission);
    }



    private static void updateSpaceships() {
    }
}