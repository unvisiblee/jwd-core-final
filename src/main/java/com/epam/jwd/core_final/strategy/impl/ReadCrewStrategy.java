package com.epam.jwd.core_final.strategy.impl;

import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.strategy.ReadStrategy;
import com.epam.jwd.core_final.util.LoggerImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadCrewStrategy implements ReadStrategy {
    @Override
    public void read(String filePath) throws InvalidStateException {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(filePath));
        } catch (FileNotFoundException ex) {
            LoggerImpl.INSTANCE.logger.error(ex.getMessage());
            throw new InvalidStateException(filePath);
        }
        scanner.nextLine();
        scanner.useDelimiter(";");

        while(scanner.hasNext()) {
            String attributesDelimitedByComma = scanner.next();
            String[] attributes = attributesDelimitedByComma.split(",");
            Role role = Role.resolveRoleById(Integer.parseInt(attributes[0]));
            String name = attributes[1];
            Rank rank = Rank.resolveRankById(Integer.parseInt(attributes[2]));
            CrewServiceImpl.INSTANCE.createCrewMember(name, role, rank);
        }
    }
}
