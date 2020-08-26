package duke.exceptions;

/**
 * Class to initiate InvalidDoneFormatException.
 * Thrown when the format for done command is wrong.
 */
public class InvalidDoneFormatException extends DukeException{
    public InvalidDoneFormatException() {
        super("☹ OOPS!!! Format for done command doesnt seem to be correct. Eg. done 2");
    }
}

