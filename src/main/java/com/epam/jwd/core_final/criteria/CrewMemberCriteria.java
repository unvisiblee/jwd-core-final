package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.CrewMember} fields
 */
public class CrewMemberCriteria extends Criteria<CrewMember> {
    private Role role;
    private Rank rank;
    private Boolean isReadyForNextMissions;

    public static class CrewMemberBuilder extends Criteria.Builder<CrewMemberBuilder> {
         private Role role;
         private Rank rank;
         private Boolean isReadyForNextMissions;

        @Override
        protected CrewMemberBuilder getThis() {
            return this;
        }

        @Override
        public Criteria<CrewMember> build() {
            CrewMemberCriteria newCriteria = new CrewMemberCriteria(getThis());
            newCriteria.rank = this.rank;
            newCriteria.role = this.role;
            newCriteria.isReadyForNextMissions = this.isReadyForNextMissions;
            return newCriteria;
        }

        public CrewMemberBuilder role(Role role) {
            this.role = role;
            return this;
        }

        public CrewMemberBuilder rank(Rank rank) {
            this.rank = rank;
            return this;
        }

        public CrewMemberBuilder isReadyForNextMissions(Boolean isReadyForNextMissions) {
            this.isReadyForNextMissions = isReadyForNextMissions;
            return this;
        }
    }

    protected CrewMemberCriteria(Builder builder) {
        super(builder);
    }

    public Role getRole() {
        return role;
    }

    public Rank getRank() {
        return rank;
    }

    public Boolean getReadyForNextMissions() {
        return isReadyForNextMissions;
    }
}
