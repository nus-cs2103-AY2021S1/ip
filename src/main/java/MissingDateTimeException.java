/**
 * Class to hold the MissingDateTimeException message of a MissingDateTimeException.
 */
public class MissingDateTimeException extends DukeException {

    /**
     * Custom message of a MissingDateTimeException, reminding the user to type in a valid Date and Time format as
     * described by this message.
     */
    @Override
    public String getMessage() {
        return Ui.LINE + "     OOPS!!! The command you keyed in needs to have a timing!\n"
                + "     The timing should be typed in after the tag, in the format \"DD/MM/YYYY\" HHMM!"
                + Ui.LINE;
    }
}
