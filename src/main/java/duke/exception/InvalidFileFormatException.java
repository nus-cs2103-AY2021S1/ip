package duke.exception;


public class InvalidFileFormatException extends DukeException {
    public InvalidFileFormatException() {
        super("CSV file is poorly formatted!");
    }
}
