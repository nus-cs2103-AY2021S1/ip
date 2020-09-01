package duke.dukeexception;

/**
 * Exception thrown when date and time is not given in the correct format
 * for <code>DEADLINE</code> and <code>EVENT</code> user commands from
 * <code>CommandType</code>.
 */
public class WrongDeadlineException extends DukeException {
    public WrongDeadlineException(String cmd, String separator) {
        super("You type wrong lah!" +
                "\nTry \"" +
                cmd +
                " {description of task} " +
                separator +
                " {date and time in this format: dd/MM/yyyy HH:mm}\"");
    }
}
