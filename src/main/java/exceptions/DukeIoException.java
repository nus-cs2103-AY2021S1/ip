package exceptions;


public class DukeIoException extends DukeException {

    private final String path;

    public DukeIoException(String message, String path) {
        super(message);
        this.path = path;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " File Path: " + path;
    }
}
