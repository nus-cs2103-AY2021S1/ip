package duke.exceptions;

public class DukeDeadlineFormatException extends DukeException {
    public final static String ERROR_DEADLINE_FORMAT = "⚠⚠⚠ The description of 'deadline' should be accompanied"
            + '\n' + "     " + "    by '/by' followed by the date in this format: 'yyyy-MM-dd HH:mm'";
    public DukeDeadlineFormatException() {
        super(ERROR_DEADLINE_FORMAT);
    }
}
