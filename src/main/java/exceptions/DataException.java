package exceptions;

/**
 * Exception representing an error in data validation.
 */
public class DataException extends IPException {

    private final String command;
    private final String reason;

    /**
     * Creates a new exception.
     * @param field the fields with invalid data
     * @param reason the reason why the data is invalid
     */
    public DataException(String field, String reason) {
        super(reason);
        this.command = field;
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Sorry, this field is invalid: " + command + "\nReason: " + reason;
    }
}
