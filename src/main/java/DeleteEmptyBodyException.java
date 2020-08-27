public class DeleteEmptyBodyException extends EmptyBodyException {
    DeleteEmptyBodyException() {}
    @Override
    public String toString() {
        return "☹ OOPS!!! Empty deletion is invalid.";
    }
}