public class InvalidDateException extends DukeException {

    public InvalidDateException() {
        super("Invalid Date Format. Use YYYY-MM-DD");
    }

    protected InvalidDateException(String msg) {
        super(msg);
    }
}