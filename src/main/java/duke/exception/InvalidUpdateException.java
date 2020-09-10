package duke.exception;

public class InvalidUpdateException extends DukeException {
    public InvalidUpdateException(String command) {
        super(command);
    }

    @Override
    public String toString() {
        return "Can't update anything! Ensure the command is in this format:" + "\n"
                + "    'update [index] [what to change] [change to what]'" + "\n\n"
                + "NOTE:" + "\n"
                + "– check if the given index is valid" + "\n"
                + "– when changing dates, check if the given date is of the form YYYY-MM-DD";
    }
}
