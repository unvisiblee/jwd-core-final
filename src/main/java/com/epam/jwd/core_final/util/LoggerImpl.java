package com.epam.jwd.core_final.util;

import org.slf4j.LoggerFactory;

public enum LoggerImpl {
    INSTANCE;

    public org.slf4j.Logger logger = LoggerFactory.getLogger(LoggerImpl.class);

    LoggerImpl() {

    }
}
