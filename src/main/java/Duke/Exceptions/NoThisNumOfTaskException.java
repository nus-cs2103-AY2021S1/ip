package Duke.Exceptions;

public class NoThisNumOfTaskException extends DukeException{
    public NoThisNumOfTaskException() {
        super(String.format("  ☹ OOPS!!! The number you input is in a wrong format or it does not exist."));
    }
}
