package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationSubMenu;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;
import com.epam.jwd.core_final.util.ConsoleColors;

import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ViewEntitiesSubMenu implements ApplicationSubMenu {
    public static void viewEntities() {
        Scanner scanner = new Scanner(System.in);
        int option;
        while (true) {
            try {
                System.out.println(ConsoleColors.BLACK_BOLD + "1. View Crew Members \n" +
                        "2. View Flight Missions \n" +
                        "3. View Spaceships \n" +
                        "0. Return to the main menu" + ConsoleColors.RESET);
                option = scanner.nextInt();
                if (option == 0)
                    break;
                handleUserInput(option);
            } catch (InputMismatchException ex) {
                System.out.println(ConsoleColors.RED + "You've entered wrong option, please try again!" + ConsoleColors.RESET);
                scanner.next();
            }
        }


        System.out.println(NassaContext.INSTANCE.retrieveBaseEntityList(FlightMission.class));
    }

    static void handleUserInput(int option) {
        switch (option) {
            case 1:
            {
                viewCrewMembers();
                break;
            }
            case 2:
            {
                viewFlightMissions();
                break;
            }
            case 3:
            {
                viewSpaceships();
                break;
            }
        }
    }

    private static void viewCrewMembers() {
        Scanner scanner = new Scanner(System.in);
        int option;
        System.out.println(ConsoleColors.GREEN_UNDERLINED + "1. View all Crew Members \n" +
                "2. View Crew Members Rank and Role \n");
        while (true) {
            try {
                option = scanner.nextInt();
                break;
            } catch (InputMismatchException ex) {
                System.out.println(ConsoleColors.RED_UNDERLINED + "You've entered wrong option, please try again" + ConsoleColors.RESET);
            }
        }

        switch (option) {
            case 1:
            {
                viewAllCrewMembers();
                break;
            }
            case 2:
            {
                viewCrewMembersByCriteria();
                break;
            }
        }
    }

    private static void viewAllCrewMembers() {
        List<CrewMember> memberList = CrewServiceImpl.INSTANCE.findAllCrewMembers();

        for (int i = 0; i < memberList.size(); i++) {
            String nameColor = "";
            if (!memberList.get(i).getReadyForNextMissions())
                nameColor = ConsoleColors.RED_BOLD;
            else
                nameColor = ConsoleColors.GREEN_BOLD;

            System.out.print(nameColor + memberList.get(i).getName() + " - " + ConsoleColors.PURPLE_BOLD +
                    memberList.get(i).getRole() + ", " + memberList.get(i).getRank().getId() + ";  ");
            if (i % 2 == 1)
                System.out.println("\n");
        }
    }

    private static void viewCrewMembersByCriteria() {
        Scanner scanner = new Scanner(System.in);
        CrewMemberCriteria.CrewMemberBuilder memberCriteriaBuilder = new CrewMemberCriteria.CrewMemberBuilder();
        System.out.println(ConsoleColors.GREEN + "Choose Crew Member Rank: \n" +
                " 1. TRAINEE,\n" +
                " 2. SECOND_OFFICER,\n" +
                " 3. FIRST_OFFICER,\n" +
                " 4. CAPTAIN;" + ConsoleColors.RESET);

        int rankId = getRoleOrRankIDFromUser(scanner);

        memberCriteriaBuilder.rank(Rank.resolveRankById(rankId));


            System.out.println(ConsoleColors.GREEN + "Choose Crew Member Role: \n" +
                    "1. MISSION_SPECIALIST,\n" +
                    "2. FLIGHT_ENGINEER,\n" +
                    "3. PILOT,\n" +
                    "4. COMMANDER;" + ConsoleColors.RESET);

        int roleId = getRoleOrRankIDFromUser(scanner);

        memberCriteriaBuilder.role(Role.resolveRoleById(roleId));

            List <CrewMember> memberList = CrewServiceImpl.INSTANCE.findAllCrewMembersByCriteria(memberCriteriaBuilder.build());

            if (memberList.size() == 0)
                System.out.println(ConsoleColors.RED + "We didn't found any Crew Members with rank and role you've chosen," +
                        "please try again later!" + ConsoleColors.RESET);
            else {
                System.out.println(ConsoleColors.BLACK_BOLD + "\n\n    NAME     |     ROLE     |    RANK     ");
                for(CrewMember member: memberList) {
                    String nameColor;
                    if (!member.getReadyForNextMissions()) // if member is free - his name is green, else - red
                        nameColor = ConsoleColors.RED_BOLD;
                    else
                        nameColor = ConsoleColors.GREEN_BOLD;

                    System.out.println(nameColor + member.getName() + " - " + ConsoleColors.PURPLE_BOLD +
                            member.getRole().getName() + ", " + member.getRank().getName() + ";  ");
                }
            }


        }


    private static void viewFlightMissions() {
        Scanner scanner = new Scanner(System.in);
        int option;
        System.out.println(ConsoleColors.GREEN_UNDERLINED + "1. View all Flight Missions \n" +
                "2. View Missions by Mission Status \n" + ConsoleColors.RESET);

        option = getIntFromUser(scanner);

        switch (option) {
            case 1:
            {
                viewAllFlightMissions();
                break;
            }
            case 2:
            {
                viewFlightMissionsByCriteria();
                break;
            }
        }
    }



    private static void viewAllFlightMissions() {
        List <FlightMission> flightMissions = MissionServiceImpl.INSTANCE.findAllMissions();

        for (FlightMission mission: flightMissions) {
            printOneMission(mission);
        }


    }

    private static void viewFlightMissionsByCriteria() {
        FlightMissionCriteria.FlightMissionCriteriaBuilder criteriaBuilder = new FlightMissionCriteria.FlightMissionCriteriaBuilder();

        System.out.println("Choose mission status:" +
                "  1. CANCELLED,\n" +
                "  2. FAILED,\n" +
                "  3. PLANNED,\n" +
                "  4. IN_PROGRESS,\n" +
                "  5. COMPLETED");

        int missionStatusFromUser = getIntFromUser(new Scanner(System.in));

        MissionResult status = null;

        if (missionStatusFromUser == 1)
            status = MissionResult.CANCELLED;
        else if (missionStatusFromUser == 2)
            status = MissionResult.FAILED;
        else if (missionStatusFromUser == 3)
            status = MissionResult.PLANNED;
        else if (missionStatusFromUser == 4)
            status = MissionResult.IN_PROGRESS;
        else if (missionStatusFromUser == 5)
            status = MissionResult.COMPLETED;

        criteriaBuilder.missionResult(status);

        List <FlightMission> missionList = MissionServiceImpl.INSTANCE.findAllMissionsByCriteria(criteriaBuilder.build());

        for (FlightMission mission: missionList) {
            printOneMission(mission);
        }

    }

    private static void printOneMission(FlightMission mission) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(NassaContext.INSTANCE.getAppProperties().getDateTimeFormat());
        System.out.println(ConsoleColors.BLUE + "Mission name - "+ ConsoleColors.PURPLE_UNDERLINED + mission.getName() + ConsoleColors.RESET +
                "\n   " + "Start and End Dates: " + ConsoleColors.GREEN_BOLD + mission.getStartDate().format(formatter) + ", " + mission.getEndDate().format(formatter) + ConsoleColors.RESET +
                "\n   Assigned Spaceship: " + ConsoleColors.BLACK_BOLD +  mission.getAssignedSpaceShip().getName() + ConsoleColors.RESET +
                "\n   Distance: " + ConsoleColors.BLACK_BOLD + mission.getDistance() + ConsoleColors.RESET +
                "\n   Status: " + ConsoleColors.PURPLE_BOLD + mission.getMissionResult() + ConsoleColors.RESET);
    }


    private static int getRoleOrRankIDFromUser(Scanner scanner) {
        int Id = 0;

        while (true) {
            try {
                Id = scanner.nextInt();
                if (Id > Role.values().length) // if we get not existing value - print error
                    throw new InputMismatchException();
                else break;
            } catch (InputMismatchException ex) {
                System.out.println(ConsoleColors.RED + "You've entered wrong number, please try again!" + ConsoleColors.RESET);
            }
        }
        return Id;
    }

    private static int getIntFromUser(Scanner scanner) {
        int option;
        while (true) {
            try {
                option = scanner.nextInt();
                break;
            } catch (InputMismatchException ex) {
                System.out.println(ConsoleColors.RED_UNDERLINED + "You've entered wrong option, please try again" + ConsoleColors.RESET);
            }
        }
        return option;
    }

    private static void viewSpaceships() {
        Scanner scanner = new Scanner(System.in);

        int option;

        System.out.println(ConsoleColors.GREEN_UNDERLINED + "1. View all Spaceships \n" +
                "2. View Spaceships by Criteria \n" + ConsoleColors.RESET);

        option = getIntFromUser(scanner);

        switch (option) {
            case 1:
            {
                viewAllSpaceships();
                break;
            }
            case 2:
            {
                viewSpaceshipsByCriteria();
                break;
            }
        }
    }


    public static void viewAllSpaceships() { // used in add mission submenu
        System.out.println(ConsoleColors.RED_UNDERLINED + "SPACESHIP NAME | SPACESHIP FLIGHT DISTANCE" + ConsoleColors.RESET);
        List <Spaceship> spaceships = SpaceshipServiceImpl.INSTANCE.findAllSpaceships();

        for (int i = 0; i < spaceships.size(); i++) {
            String nameColor = null;
            if (spaceships.get(i).isReadyForNextMissions())
                nameColor = ConsoleColors.GREEN_BOLD;
            else nameColor = ConsoleColors.RED_BOLD;

            System.out.print(nameColor + spaceships.get(i).getName() + " - "+ ConsoleColors.WHITE_UNDERLINED + spaceships.get(i).getFlightDistance() +
                    ConsoleColors.RESET + ", ");
            if (i % 2 == 1)
                System.out.print("\n");
        }
    }


    private static void viewSpaceshipsByCriteria() {


    }

}