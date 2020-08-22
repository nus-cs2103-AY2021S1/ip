package exceptions;

/**
 * An exception results from service failure
 */
public class ServiceException extends DukeException {
    public ServiceException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Error when handling: " + super.message;
    }
}
