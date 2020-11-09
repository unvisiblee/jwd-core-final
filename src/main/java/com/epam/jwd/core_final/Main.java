package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.strategy.impl.ReadCrewStrategy;
import com.epam.jwd.core_final.strategy.impl.ReadSpaceshipsStrategy;
import com.epam.jwd.core_final.util.Logger;
import com.epam.jwd.core_final.util.PropertyReaderUtil;

import java.io.FileNotFoundException;


public class Main {

    public static void main(String[] args) {
        /*System.out.println(System.getProperty("user.dir"));
        ApplicationProperties applicationProperties = PropertyReaderUtil.getApplicationProperties();
        System.out.println("h");
        Criteria<CrewMember> crewMemberCriteria = new CrewMemberCriteria.CrewMemberBuilder().id(2).isReadyForNextMissions(true).id(1).name("test").build();
        Logger.INSTANCE.logger.info("test");
        Application.start();
        System.out.println(Role.COMMANDER.getName());*/

        ReadCrewStrategy readCrewStrategy = new ReadCrewStrategy();
        readCrewStrategy.read("/home/unvisiblee/IdeaProjects/jwd-core-final/src/main/resources/input/crew");
        ReadSpaceshipsStrategy readSpaceshipsStrategy = new ReadSpaceshipsStrategy();
        readSpaceshipsStrategy.read("/home/unvisiblee/IdeaProjects/jwd-core-final/src/main/resources/input/spaceships");


        System.out.println(NassaContext.INSTANCE.retrieveBaseEntityList(CrewMember.class));
        System.out.println(NassaContext.INSTANCE.retrieveBaseEntityList(Spaceship.class));


    }
}