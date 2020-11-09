package duke.core;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Manage loggers in duke.core
 */
public class DukeLogger {

    private static final Logger logger = Logger.getLogger(DukeLogger.class.getName());
    private static final Logger parentLogger = logger.getParent();

    /**
     * Set log level of items in duke.core
     * @param logLevel The logger verbosity
     */
    public static void setLevel(Level logLevel) {
        parentLogger.setLevel(logLevel);
    }

    /**
     * Write the log to specified output file
     * @param filename File to write log into
     * @param append append to the file if true, else overwrites existing contents
     */
    public static void addOutputFile(String filename, boolean append) {
        try {
            FileHandler fileHandler = new FileHandler(filename, append);
            fileHandler.setFormatter(new SimpleFormatter());
            parentLogger.addHandler(fileHandler);
        } catch (Exception e) {
            // TODO: proper exception handling
            System.err.println("Failed to add logger file handler");
        }
    }

}
