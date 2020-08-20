public class MissingTimeException extends Exception {
    public MissingTimeException() {
        super("\n     ☹ OOPS!!! The time of an event cannot be empty.");
    }
}
