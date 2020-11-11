package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.exception.InvalidStateException;

import java.io.FileNotFoundException;
import java.util.function.Supplier;

public interface Application {

    static ApplicationMenu start() throws InvalidStateException {
        final Supplier<ApplicationContext> applicationContextSupplier = NassaContext::new;
        final ApplicationContext nassaContext = applicationContextSupplier.get();
        nassaContext.init();



        return applicationContextSupplier::get;
    }
}
