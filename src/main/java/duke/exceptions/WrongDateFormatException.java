package duke.exceptions;

/**
 * Exception thrown when a invalid datetime String that cannot be parsed is provided.
 */
public class WrongDateFormatException extends DukeException {

    /**
     * Constructs a new WrongDateFormatException.
     *
     * @param correctFormat String representing the correct format a datetime String should be in
     */
    public WrongDateFormatException(String correctFormat) {
        super("Wrong datetime format",
                "Wrong datetime format provided, date and time should be specified as follows: "
                        + correctFormat);
    }

}
