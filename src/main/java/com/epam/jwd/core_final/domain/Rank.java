package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.exception.UnknownEntityException;

public enum Rank implements BaseEntity {
    TRAINEE(1L),
    SECOND_OFFICER(2L),
    FIRST_OFFICER(3L),
    CAPTAIN(4L);

    private final Long id;

    Rank(Long id) {
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
     *
     * @throws UnknownEntityException if such id does not exist
     */
    public static Rank resolveRankById(int id) throws  UnknownEntityException {
        for (Rank r: Rank.values()) {
            if (r.id.equals((long) id))
                return r;
        }
        throw new UnknownEntityException(String.valueOf(id));
    }
}
