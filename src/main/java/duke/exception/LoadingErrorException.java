package duke.exception;

public class LoadingErrorException extends DukeException {
    public LoadingErrorException(String path) {
        super("Error! No file found at path: " + path);
    }
}
