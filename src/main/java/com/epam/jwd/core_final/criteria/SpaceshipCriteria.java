package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;

import java.util.HashMap;

/**
 * Should be a builder for {@link Spaceship} fields
 */
public class SpaceshipCriteria extends Criteria<Spaceship> {
    private HashMap<Role, Short> crew;
    private Long flightDistance;
    private boolean isReadyForNextMissions;

    public static class SpaceshipCriteriaBuilder extends Builder<SpaceshipCriteriaBuilder> {

        private HashMap<Role, Short> crew;
        private Long flightDistance;
        private boolean isReadyForNextMissions;

        @Override
        protected SpaceshipCriteriaBuilder getThis() {
            return this;
        }

        public SpaceshipCriteriaBuilder crew(HashMap<Role, Short> crew) {
            this.crew = crew;
            return this;
        }

        public SpaceshipCriteriaBuilder flightDistance(Long flightDistance) {
            this.flightDistance = flightDistance;
            return this;
        }

        public SpaceshipCriteriaBuilder  readyForNextMissions(boolean readyForNextMissions) {
            isReadyForNextMissions = readyForNextMissions;
            return this;
        }

        @Override
        public Criteria<? extends BaseEntity> build() {
            SpaceshipCriteria newCriteria =  new SpaceshipCriteria(this);
            newCriteria.setCrew(this.crew);
            newCriteria.setFlightDistance(this.flightDistance);
            newCriteria.setReadyForNextMissions(this.isReadyForNextMissions);
            return newCriteria;
        }
    };

    protected SpaceshipCriteria(Builder<?> builder) {
        super(builder);
    }

    public HashMap<Role, Short> getCrew() {
        return crew;
    }

    public Long getFlightDistance() {
        return flightDistance;
    }

    public boolean isReadyForNextMissions() {
        return isReadyForNextMissions;
    }

    public void setCrew(HashMap<Role, Short> crew) {
        this.crew = crew;
    }

    public void setFlightDistance(Long flightDistance) {
        this.flightDistance = flightDistance;
    }

    public void setReadyForNextMissions(boolean readyForNextMissions) {
        isReadyForNextMissions = readyForNextMissions;
    }
}
