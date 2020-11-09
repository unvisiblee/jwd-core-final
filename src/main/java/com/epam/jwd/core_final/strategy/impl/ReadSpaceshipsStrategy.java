package com.epam.jwd.core_final.strategy.impl;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;
import com.epam.jwd.core_final.strategy.ReadStrategy;
import com.epam.jwd.core_final.util.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class ReadSpaceshipsStrategy implements ReadStrategy {
    @Override
    public void read(String filePath) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(filePath));
        } catch (FileNotFoundException ex) {
            Logger.INSTANCE.logger.error(ex.getMessage());
            return;
        }
        scanner.nextLine();
        scanner.nextLine();
        scanner.nextLine();

        while(scanner.hasNextLine()) {
            String argsDelimitedBySemiColumns = scanner.nextLine();
            String[] args = argsDelimitedBySemiColumns.split(";");
            String name = args[0];
            Long distance = Long.valueOf(args[1]);
            String[] mapArgs = args[2].substring(1, args[2].length() - 1).split(","); // cutting off the braces
            System.out.println(Arrays.toString(mapArgs));
            HashMap<Role, Short> crewMap = new HashMap<>();
            for (String roleCountCouple: mapArgs) {
                String[] roleCountArray = roleCountCouple.split(":");
                crewMap.put(Role.resolveRoleById(Integer.parseInt(roleCountArray[0])), Short.parseShort(roleCountArray[1]));
            }
            SpaceshipServiceImpl.INSTANCE.createSpaceship(name, crewMap, distance);
        };
    }
}
