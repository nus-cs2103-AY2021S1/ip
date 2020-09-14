package duke.exception;

/**
 * This exception thrown when a file contains tasks in an unreadable format.
 */
public class UnreadableSaveTaskException extends Exception {
    @Override
    public String toString() {
        return "This task is saved in an invalid format! Did you tamper with it?";
    }
}
