package duke;

public class InvalidDateTimeInput extends DukeException {
    public InvalidDateTimeInput() {
        super("Sorry please enter a valid date and time!");
    }
}
