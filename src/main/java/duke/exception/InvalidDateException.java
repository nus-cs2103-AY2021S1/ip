package duke.exception;

/**
 * This exception is thrown when an invalid date format is parsed.
 */
public class InvalidDateException extends Exception {
    @Override
    public String toString() {
        return "Invalid date format detected!\n"
                + "Accepted formats: 'dd/MM/yyyy' or 'dd-MM-yyyy";
    }
}
