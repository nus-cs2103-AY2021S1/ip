package exceptions;

/**
 * If event or deadline does not have a time,
 * <tt>DukeMissingTimeException</tt> will be thrown.
 */
public class DukeMissingTimeException extends DukeException {

    /**
     * Override the message specifically that will be thrown
     * to user when there is missing time in event or deadline command.
     */
    public DukeMissingTimeException() {
        super("Sorry Boss! Please ensure that you have input the time");
    }
}
