package exception;


/**
 * Thrown to notify user that the date time format
 * entered in the command does not satisfy the format
 * that was hoped in the program.
 */
public class DukeDateTimeParserException extends DukeException {

    /**
     * Returns a short description of this throwable.
     * The result is "OOPS!! You have to enter the
     * date time format correctly".
     *
     * @return String exception message.
     */
    @Override
    public String toString() {
        return super.toString() + " You have to enter the date time format correctly";
    }
}
