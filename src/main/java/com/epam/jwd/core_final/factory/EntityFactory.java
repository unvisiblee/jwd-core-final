package com.epam.jwd.core_final.factory;

import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.exception.IllegalCreateArgsException;

public interface EntityFactory<T extends BaseEntity> {

    public T create(Object... args) throws IllegalCreateArgsException;
}
