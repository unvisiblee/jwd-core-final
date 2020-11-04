package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.exception.UnknownEntityException;

public enum Role implements BaseEntity {
    MISSION_SPECIALIST(1L),
    FLIGHT_ENGINEER(2L),
    PILOT(3L),
    COMMANDER(4L);

    private final Long id;

    Role(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    /**
     * via java.lang.enum methods! done
     */
    @Override
    public String getName() {
        return this.name();
    }

    /**
     * via java.lang.enum methods! done
     * @throws UnknownEntityException if such id does not exist
     */
    public static Role resolveRoleById(int id) throws UnknownEntityException {
        for (Role role: Role.values()) {
            if (role.id.equals((long) id))
                return role;
        }
        throw new UnknownEntityException(String.valueOf(id));
    }
}
