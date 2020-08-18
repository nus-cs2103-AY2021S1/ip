public class DukeIndexOutOfBoundsException extends DukeException {
    public DukeIndexOutOfBoundsException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "OOPS!!! That index is out of range!";
    }
}