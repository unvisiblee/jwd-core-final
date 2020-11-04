package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.ApplicationProperties;

import java.io.*;
import java.util.Properties;


public enum PropertyReaderUtil {
    INSTANCE;

    private static final Properties properties = new Properties();

    /**
     * try-with-resource using FileInputStream
     *
     * @see {https://www.netjstech.com/2017/09/how-to-read-properties-file-in-java.html for an example}
     * <p>
     * as a result - you should populate {@link ApplicationProperties} with corresponding
     * values from property file
     */
    private static void loadProperties()  {
        final String propertiesFileName = "/home/unvisiblee/IdeaProjects/jwd-core-final/src/main/resources/application.properties"; // todo: replace with universal path

        try(InputStream inputStream = new FileInputStream(propertiesFileName)) {
            properties.load(inputStream);
        } catch (IOException e) {
            Logger.INSTANCE.logger.error(e.getMessage());
        }
    }

    public static ApplicationProperties getApplicationProperties() {
        loadProperties();
        String inputRootDir = properties.getProperty("inputRootDir");
        String outputRootDir = properties.getProperty("outputRootDir");
        String crewFileName = properties.getProperty("crewFileName");
        String missionsFileName = properties.getProperty("missionsFileName");
        String spaceshipsFileName = properties.getProperty("spaceshipsFileName");
        Integer fileRefreshRate = Integer.parseInt(properties.getProperty("fileRefreshRate"));
        String dateTimeFormat = properties.getProperty("dateTimeFormat");

        return new ApplicationProperties(inputRootDir, outputRootDir, crewFileName,
                missionsFileName, spaceshipsFileName, fileRefreshRate, dateTimeFormat);
    }
}
