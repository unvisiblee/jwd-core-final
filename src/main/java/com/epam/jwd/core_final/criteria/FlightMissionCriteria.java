package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Spaceship;

import java.time.LocalDate;
import java.util.List;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.FlightMission} fields
 */
public class FlightMissionCriteria extends Criteria<FlightMission> {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long distance;
    private Spaceship assignedSpaceShip;
    private List<CrewMember> assignedCrew;
    private MissionResult missionResult;


    public static class FlightMissionCriteriaBuilder extends Builder<FlightMissionCriteria.FlightMissionCriteriaBuilder> {
        private String name;
        private LocalDate startDate;
        private LocalDate endDate;
        private Long distance;
        private Spaceship assignedSpaceShip;
        private List<CrewMember> assignedCrew;
        private MissionResult missionResult;

        public FlightMissionCriteriaBuilder name(String newName) {
            this.name = name;
            return this;
        }

        public FlightMissionCriteriaBuilder startDate(LocalDate newStartDate) {
            this.startDate = newStartDate;
            return this;
        }

        public FlightMissionCriteriaBuilder endDate(LocalDate newEndDate) {
            this.endDate = newEndDate;
            return this;
        }

        public FlightMissionCriteriaBuilder distance(Long newDistance) {
            this.distance = newDistance;
            return this;
        }

        public FlightMissionCriteriaBuilder assignedSpaceShip(Spaceship newSpaceShip) {
            this.assignedSpaceShip = newSpaceShip;
            return this;
        }

        public FlightMissionCriteriaBuilder assignedCrew(List<CrewMember> newCrew) {
            this.assignedCrew = newCrew;
            return this;
        }

        public FlightMissionCriteriaBuilder missionResult(MissionResult newMissionResult) {
            this.missionResult = newMissionResult;
            return this;
        }

        @Override
        protected FlightMissionCriteriaBuilder getThis() {
            return this;
        }

        @Override
        public Criteria<FlightMission> build() {
            FlightMissionCriteria criteria = new FlightMissionCriteria(this);
            criteria.assignedCrew = this.assignedCrew;
            criteria.assignedSpaceShip = this.assignedSpaceShip;
            criteria.distance = this.distance;
            criteria.endDate = this.endDate;
            criteria.startDate = this.startDate;
            criteria.missionResult = this.missionResult;
            return criteria;
        }
    }


    protected FlightMissionCriteria(Builder builder) {
        super(builder);
    }

    @Override
    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Long getDistance() {
        return distance;
    }

    public Spaceship getAssignedSpaceShip() {
        return assignedSpaceShip;
    }

    public List<CrewMember> getAssignedCrew() {
        return assignedCrew;
    }

    public MissionResult getMissionResult() {
        return missionResult;
    }

}
