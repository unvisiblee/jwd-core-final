package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.util.ConsoleColors;
import com.epam.jwd.core_final.util.LoggerImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public interface ApplicationSubMenu {

    static int getIntFromUser(Scanner scanner) {
        int option;
        while (true) {
            try {
                option = scanner.nextInt();
                break;
            } catch (InputMismatchException ex) {
                System.out.println(ConsoleColors.RED_UNDERLINED + "You've entered wrong option, please try again" + ConsoleColors.RESET);
                LoggerImpl.INSTANCE.logger.error("Input mismatch!" + scanner.next());
            }
        }
        return option;
    }

    static int getRoleOrRankIDFromUser(Scanner scanner) {
        int id;

        while (true) {
            try {
                id = scanner.nextInt();
                if (id > Role.values().length) // if we get not existing value - print error
                    throw new InputMismatchException();
                else break;
            } catch (InputMismatchException ex) {
                System.out.println(ConsoleColors.RED + "You've entered wrong number, please try again!" + ConsoleColors.RESET);
                LoggerImpl.INSTANCE.logger.error("Input mismatch!");
            }
        }
        return id;
    }
}
