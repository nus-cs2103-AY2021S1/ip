package duke.exceptions;

/**
 * The exception deals with wrong format of tasks.
 */
public class NoSuchOrderException extends DukeException{
    public NoSuchOrderException() {
        super(String.format("  ☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
    }
}
