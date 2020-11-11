package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.strategy.impl.ReadCrewStrategy;
import com.epam.jwd.core_final.strategy.impl.ReadSpaceshipsStrategy;
import com.epam.jwd.core_final.util.InputFilesUpdateController;
import com.epam.jwd.core_final.util.LoggerImpl;


public class Main {

    public static void main(String[] args) {

        LoggerImpl.INSTANCE.logger.info("Starting application");

        ApplicationMenu menu = null;
        try {
            menu = Application.start();
         } catch (InvalidStateException ex) {
             LoggerImpl.INSTANCE.logger.error("Unable to start the app");
             System.exit(1);
        }

        menu.printAvailableOptions();




    }
}