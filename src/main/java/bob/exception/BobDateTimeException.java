package bob.exception;


public class BobDateTimeException extends BobException {
    public String getMessage() {
        return "Please provide dates using the following format: YYYY-MM-DD";
    }
}
