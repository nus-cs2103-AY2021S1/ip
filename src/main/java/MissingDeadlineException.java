public class MissingDeadlineException extends Exception {
    public MissingDeadlineException() {
        super("\n     ☹ OOPS!!! The deadline cannot be empty.");
    }
}
