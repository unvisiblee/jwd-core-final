package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.AddNewMissionSubMenu;
import com.epam.jwd.core_final.context.impl.ExportInfoInJSONFormatSubMenu;
import com.epam.jwd.core_final.context.impl.UpdateEntitiesSubMenu;
import com.epam.jwd.core_final.context.impl.ViewEntitiesSubMenu;
import com.epam.jwd.core_final.util.InputFilesUpdateController;

import java.util.InputMismatchException;
import java.util.Scanner;

@FunctionalInterface
public interface ApplicationMenu {

    ApplicationContext getApplicationContext();

    default Integer printAvailableOptions() {

         Scanner scanner = new Scanner(System.in);
         int option;
         while (true) {
             InputFilesUpdateController.checkFilesLastModifiedTime();

             System.out.println("1. Add new mission\n" +
                     "2. View entities\n" +
                     "3. Update entities\n" +
                     "4. Print info in JSON format\n" +
                     "0. Close the app");

             try {
                 option = scanner.nextInt();
                 if (option == 0)
                     break;

                 handleUserInput(option);
             } catch (InputMismatchException ex) {
                 System.out.println("You've entered wrong option, please try again: ");
                 scanner.next();
             }
         }
        return 0;
    }

    default Object handleUserInput(Integer option) {
        switch (option) {
            case 1:
            {
                AddNewMissionSubMenu.addMission();
                break;
            }

            case 2:
            {
                ViewEntitiesSubMenu.viewEntities();
                break;
            }

            case 3:
            {
                UpdateEntitiesSubMenu.updateEntities();
                break;
            }

            case 4:
            {
                ExportInfoInJSONFormatSubMenu.exportInfo();
                break;
            }

            case 5:
            {
                return null;
            }
        }
        return null;
    }
}
