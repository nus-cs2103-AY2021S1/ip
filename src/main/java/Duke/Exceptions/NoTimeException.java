package Duke.Exceptions;

public class NoTimeException extends DukeException{
    public NoTimeException(String task) {
        super(String.format("  ☹ OOPS!!! The time of a %s cannot be empty.", task));
    }
}
