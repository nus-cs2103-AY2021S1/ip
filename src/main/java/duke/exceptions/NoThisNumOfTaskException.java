package duke.exceptions;

/**
 * The exception deals with the situation that
 * the number is out of the range of the list.
 */
public class NoThisNumOfTaskException extends DukeException{
    public NoThisNumOfTaskException() {
        super(String.format("  ☹ OOPS!!! The number you input is in a wrong format or it does not exist."));
    }
}
