package com.epam.jwd.core_final.strategy;

import com.epam.jwd.core_final.exception.InvalidStateException;

import java.io.FileNotFoundException;

public interface ReadStrategy {
    public void read(String filePath) throws FileNotFoundException, InvalidStateException;
}
