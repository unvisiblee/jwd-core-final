package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.IllegalCrewMemberCreateArgsException;
import com.epam.jwd.core_final.factory.EntityFactory;

// do the same for other entities
public class CrewMemberFactory implements EntityFactory<CrewMember> {

    public CrewMember create(Object... args) throws IllegalCrewMemberCreateArgsException {
        // String name, Role role, Rank rank - arguments
        if (args.length != 3)
            throw new IllegalCrewMemberCreateArgsException(args);

        return new CrewMember((String)args[0], (Role) args[1], (Rank) args[2]);
    }
}
