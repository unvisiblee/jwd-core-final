package com.epam.jwd.core_final.strategy;

import java.io.FileNotFoundException;

public interface ReadStrategy {
    public void read(String filePath) throws FileNotFoundException;
}
