public class InvalidDeleteException extends DukeException {
    protected InvalidDeleteException() {
        super("Delete format is invalid.\n    Please try again with a proper format like 'delete 3'");
    }
}
