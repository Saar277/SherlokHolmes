package org.example.logger;

import org.apache.logging.log4j.*;

public class Log4JLogger {
    private static Log4JLogger log4JLogger;
    private static Logger logger;

    private Log4JLogger(Class c) {
        logger = LogManager.getLogger(c);
    }

    public static Log4JLogger getInstance(Class c) {
        if (log4JLogger == null) {
            log4JLogger = new Log4JLogger(c);
        } else {
            changeLoggerClass(c);
        }

        return log4JLogger;
    }

    public void info(String string) {
        logger.info(string);
    }

    public void error(String string) {
        logger.error(string);
    }

    private static void changeLoggerClass(Class c) {
        logger = LogManager.getLogger(c);
    }

    public Logger getLogger() {
        return logger;
    }
}
