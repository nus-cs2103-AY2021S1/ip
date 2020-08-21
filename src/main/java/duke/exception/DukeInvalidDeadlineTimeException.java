package duke.exception;

public class DukeInvalidDeadlineTimeException extends DukeInvalidTaskTimeException {

    @Override
    public String toString() {
        return "ERROR: Usage: <deadline> <description> /by <time>\n"
                + "    Time formatting: dd-MM-yyyy HH:mm";
    }

}
