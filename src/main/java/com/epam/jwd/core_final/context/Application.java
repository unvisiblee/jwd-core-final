package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.exception.InvalidStateException;

import java.util.function.Supplier;

public interface Application {

    static ApplicationMenu start() throws InvalidStateException {
        final Supplier<ApplicationContext> applicationContextSupplier = NassaContext::new; // todo
        final NassaContext nassaContext = new NassaContext();

      /*  ApplicationMenu menu = Application.start();
        menu.printAvailableOptions();

        ApplicationMenu menu1 = () -> {
            NassaContext nassaContext = new NassaContext();
            nassaContext.init();
            return nassaContext;
        };*/
        nassaContext.init();
        return applicationContextSupplier::get;
    }
}
