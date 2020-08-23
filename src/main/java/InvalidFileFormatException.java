public class InvalidFileFormatException extends DukeException {
    protected InvalidFileFormatException() {
        super("CSV file is poorly formatted!");
    }
}
