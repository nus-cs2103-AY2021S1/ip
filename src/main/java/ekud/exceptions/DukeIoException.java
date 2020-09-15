package ekud.exceptions;


public class DukeIoException extends DukeException {

    private final String path;

    /**
     * Instantiates a new ekud.Duke io exception.
     *
     * @param message the message
     * @param path    the path of the file which was invalid
     */
    public DukeIoException(String message, String path) {
        super(message);
        this.path = path;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " File Path: " + path;
    }
}
