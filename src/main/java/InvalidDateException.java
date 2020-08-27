/**
 * Represents an Invalid Date Exception . An <code>Invalid Date Exception
 * </code> object is used when date is invalid
 */
public class InvalidDateException extends Exception {
    InvalidDateException() {}
    @Override
    public String toString() {
        return "☹ OOPS!!! Date is invalid.";
    }
}