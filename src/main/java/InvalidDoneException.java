public class InvalidDoneException extends Exception {
    InvalidDoneException() {}
    @Override
    public String toString() {
        return "☹ OOPS!!! Done index is invalid.";
    }
}