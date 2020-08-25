package duke.storage;

/**
 * Exception indicating that an invalid symbol was read from the save file by Storage.
 */
public class InvalidSymbolException extends Exception {

    /**
     * Constructor to customise the message attached to the exception
     * @param description message detailing the invalid symbol
     */
    public InvalidSymbolException(String description) {
        super(description);
    }

}
