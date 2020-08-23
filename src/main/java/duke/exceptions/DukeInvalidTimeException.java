package duke.exceptions;

public class DukeInvalidTimeException extends DukeException {
    public DukeInvalidTimeException() {
        super("Your command has to be in a proper format like " +
                "the one here \n\t <command> <activity> <by/at> <Year-Month-Day Hour:Minute>.");
    }
}
