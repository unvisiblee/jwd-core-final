package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationSubMenu;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.util.ConsoleColors;
import com.epam.jwd.core_final.util.LoggerImpl;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ExportInfoInJSONFormatSubMenu implements ApplicationSubMenu {

    public static void exportInfo() {

        System.out.println(ConsoleColors.GREEN_BOLD + "Choose Export mode: \n" +
                "1. Export information about 1 mission\n" +
                "2. Export information about all missions \n" + ConsoleColors.RESET);
        int option = ApplicationSubMenu.getIntFromUser(new Scanner(System.in));

        if (option == 1)
            exportInfoAboutOneMission();
        if (option == 2)
            exportInfoAboutAllMissions();
    }

    private static void exportInfoAboutAllMissions() {
        System.out.println();
        ObjectMapper objectMapper = new ObjectMapper();
        List<FlightMission> flightMissions = MissionServiceImpl.INSTANCE.findAllMissions();
        for (FlightMission flightMission : flightMissions) {
            try {
                String filepath = "output/" + flightMission.getName() + ".json";
                objectMapper.writeValue(new File(filepath), flightMission);
            } catch (IOException ex) {
                LoggerImpl.INSTANCE.logger.error("Error exporting object in JSON " + flightMission.getName());
            }
        }
    }

    private static void exportInfoAboutOneMission() {
        ViewEntitiesSubMenu.viewAllFlightMissions();

        System.out.println(ConsoleColors.GREEN + "Choose mission's ID to Export info about: " + ConsoleColors.RESET);
        int missionId = ApplicationSubMenu.getIntFromUser(new Scanner(System.in));

        FlightMissionCriteria.FlightMissionCriteriaBuilder criteriaBuilder = new FlightMissionCriteria.FlightMissionCriteriaBuilder();
        criteriaBuilder.id((long) missionId);

        Optional<FlightMission> flightMission = MissionServiceImpl.INSTANCE.findMissionByCriteria(criteriaBuilder.build());

        if (flightMission.isEmpty()) {
            System.out.println(ConsoleColors.RED + "Mission with id you've entered does not exist!" + ConsoleColors.RESET);
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String filepath = "output/" + flightMission.get().getName() + ".json";
            objectMapper.writeValue(new File(filepath), flightMission);
        } catch (IOException ex) {
            LoggerImpl.INSTANCE.logger.error("Error exporting object in JSON " + flightMission.get().getName());
        }


    }

}