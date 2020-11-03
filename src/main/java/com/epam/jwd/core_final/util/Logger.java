package com.epam.jwd.core_final.util;

import org.slf4j.LoggerFactory;

public enum Logger {
    INSTANCE;

    public org.slf4j.Logger logger = LoggerFactory.getLogger(Logger.class);
}
