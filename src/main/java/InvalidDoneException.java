public class InvalidDoneException extends DukeException {
    protected InvalidDoneException() {
        super("Done format is invalid.\n    Please try again with a proper format like 'done 3'");
    }
}