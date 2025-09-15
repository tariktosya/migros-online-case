package utils;

import java.io.IOException;
import java.util.logging.*;

public class MyLogger {

    private static final String FILE_NAME = "logs.txt";
    private static final Logger logger = Logger.getLogger("MyLogger");

    static {
        try {

            SimpleFormatter simpleFormatter = new SimpleFormatter() {
                @Override
                public synchronized String format(LogRecord lr) { //avoid multi-thread use
                    return lr.getMessage() + System.lineSeparator();
                }
            };

            ConsoleHandler ch = new ConsoleHandler();
            ch.setFormatter(simpleFormatter);
            logger.addHandler(ch);

            FileHandler fh = new FileHandler(FILE_NAME, true);
            fh.setFormatter(simpleFormatter);
            logger.addHandler(fh);

            logger.setUseParentHandlers(false);
            logger.setLevel(Level.ALL);
        } catch (IOException e) {
            MyLogger.error("Error while writing courier location to file " + e.getMessage());
        }
    }

    public static void info(String msg) {
        logger.info(msg);
    }

    public static void warn(String msg) {
        logger.warning(msg);
    }

    public static void error(String msg) {
        logger.severe(msg);
    }
}
