package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.factory.EntityFactory;

/**
 * Should be a builder for {@link BaseEntity} fields
 */
public abstract class Criteria<T extends BaseEntity> {
    private String name;
    private Integer id;

    public abstract static class Builder<T extends Builder<T>> {
        private String name;
        private Integer id;

        protected abstract T getThis(); // method to avoid unchecked casts ( for ex. return (T) this)

        public T name(String newName) {
            this.name = newName;
            return getThis();
        }

        public T id(Integer newId) {
            this.id = newId;
            return getThis();
        }

        public abstract Criteria<? extends BaseEntity> build();
    }

    protected Criteria(Builder<?> builder) {
        this.name = builder.name;
        this.id = builder.id;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }
}
