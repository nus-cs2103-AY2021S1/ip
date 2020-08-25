package duke.exceptions;

public class NoTaskChosenException extends DukeException{
    public NoTaskChosenException(String task) {
        super(String.format("  ☹ OOPS!!! The task of a %s operation cannot be empty.", task));
    }
}
