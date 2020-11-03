package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.util.PropertyReaderUtil;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class Main {

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        ApplicationProperties applicationProperties = PropertyReaderUtil.getApplicationProperties();
        System.out.println("h");
        Criteria<CrewMember> crewMemberCriteria = new CrewMemberCriteria.CrewMemberBuilder().id(2).isReadyForNextMissions(true).id(1).name("test").build();

        //Application.start();
    }
}