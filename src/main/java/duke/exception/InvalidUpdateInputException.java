package duke.exception;

public class InvalidUpdateInputException extends DukeException {

    public InvalidUpdateInputException() {
        super("OOPS!!! Invalid input after update command. Keep index within list range.\n"
                + "(Format (for ToDo): update INDEX [d/DESCRIPTION])\n"
                + "(Format (for Deadline and Event): update INDEX [d/DESCRIPTION] [dt/yyyy/mm/dd HHmm])\n"
                + "Provide at least one of the optional fields.\n"
                + "Follow field order if both are added.");
    }
}
