package exceptions;


public class DukeIOException extends DukeException {

    private final String path;

    public DukeIOException(String message,String path) {
        super(message);
        this.path = path;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " File Path: " + path;
    }
}
