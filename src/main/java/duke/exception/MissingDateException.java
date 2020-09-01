package duke.exception;

/**
 * This exception is thrown when a task creation command lacks a date field.
 */
public class MissingDateException extends Exception {
    @Override
    public String toString() {
        return "Hey, you need to tell me the date for this.";
    }
}
