package duke.exceptions;

public class DukeEventFormatException extends DukeException{
    final static String ERROR_EVENT_FORMAT = "⚠⚠⚠ The description of 'event' should be accompanied"
            + '\n' + "     " + "    by '/at' followed by the date in this format: 'yyyy-mm-dd'";
    public DukeEventFormatException() {
        super(ERROR_EVENT_FORMAT);
    }
}
