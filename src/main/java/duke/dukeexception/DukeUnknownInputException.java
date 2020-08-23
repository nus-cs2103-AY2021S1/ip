package duke.dukeexception;


public class DukeUnknownInputException extends DukeException {
    public DukeUnknownInputException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
