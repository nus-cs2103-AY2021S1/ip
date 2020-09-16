package duke_exceptions;

/**
 * Represents an Invalid Date Exception . An <code>Invalid Date Exception
 * </code> object is used when date is invalid
 */
public class InvalidDateException extends Exception {
    /** Empty constructor as only toString method is repetitively used.
     *
     */
    InvalidDateException() { }

    /** Returns a string message for invalid date body exception.
     *
     */
    @Override
    public String toString() {
        return "Date is invalid.";
    }
}
