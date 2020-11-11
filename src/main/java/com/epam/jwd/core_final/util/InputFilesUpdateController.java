package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.exception.InvalidStateException;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public enum InputFilesUpdateController {
    INSTANCE;

    private static LocalDateTime lastTimeInited = LocalDateTime.now();
    private static String crewInputPath = PropertyReaderUtil.getApplicationProperties().getCrewFileName();
    private static String spaceShipInputPath = PropertyReaderUtil.getApplicationProperties().getSpaceshipsFileName();

    private static LocalDateTime lastDateTimeModified = LocalDateTime.MIN;

    public static void checkFilesLastModifiedTime() {
        if (LocalDateTime.now().minusMinutes((long) PropertyReaderUtil.getApplicationProperties().getFileRefreshRate()).isAfter(lastTimeInited)) {
            lastTimeInited = LocalDateTime.now();

            long lastModifiedEpochMillis = Math.max(new File(crewInputPath).lastModified(), (new File(spaceShipInputPath).lastModified()));
            LocalDateTime lastTimeModified = LocalDateTime.ofEpochSecond(lastModifiedEpochMillis/1000,0, ZoneOffset.ofHours(3));
            try {
                if (lastTimeModified.isAfter(lastDateTimeModified)) {
                    NassaContext.INSTANCE.init();
                    lastDateTimeModified = lastTimeModified;
                }
            } catch (InvalidStateException ex) {
                LoggerImpl.INSTANCE.logger.error("Unsuccessful reinit, check input files! \n" + ex.getMessage());
            }
        }
    }
}