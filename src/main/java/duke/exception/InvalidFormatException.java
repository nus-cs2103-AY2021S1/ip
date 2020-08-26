package duke.exception;

public class InvalidFormatException extends DukeException {

    public InvalidFormatException(String instructionFormat) {
        super("Please use the correct format for your instructions!",
                InvalidFormatException.class.getName(), "Format - " + instructionFormat);
    }
}
