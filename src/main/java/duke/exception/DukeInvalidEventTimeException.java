package duke.exception;

public class DukeInvalidEventTimeException extends DukeInvalidTaskTimeException {

    @Override
    public String toString() {
        return "ERROR: Usage: <event> <description> /at <time>\n"
                + "    Time formatting: dd-MM-yyyy HH:mm";
    }

}
