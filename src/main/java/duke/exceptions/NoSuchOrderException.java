package duke.exceptions;

public class NoSuchOrderException extends DukeException{
    public NoSuchOrderException() {
        super(String.format("  ☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
    }
}
