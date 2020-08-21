package exceptions;

public class WrongDateFormatException extends DukeException {

    public WrongDateFormatException(String correctFormat) {
        super("Wrong datetime format",
                "Wrong datetime format provided, date and time should be specified as follows: " +
                        correctFormat);
    }

}
