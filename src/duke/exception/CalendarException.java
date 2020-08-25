package duke.exception;

public class CalendarException extends DukeException {

    /**
     * Constructs the calendar exception
     * for invalid format of date and time.
     * @param msg Error message.
     */
    public CalendarException(String msg) {
        super(msg);
    }

}