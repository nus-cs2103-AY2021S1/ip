package duke.exception;

/**
 * The exception to be thrown to indicate that Duke did not find an existing data file and created a new one.
 */
public class NoDataFileException  extends Exception {
    /**
     * Construct a new exception to indicate that no data file found and Duke created a new empty one.
     */
    public NoDataFileException() {
        super("No data file found, a new data file created!");
    }
}
