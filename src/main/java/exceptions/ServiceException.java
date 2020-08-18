package exceptions;

public class ServiceException extends DukeException {
    public ServiceException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Error when handling: " + super.message;
    }
}
