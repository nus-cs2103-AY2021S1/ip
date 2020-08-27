public class DeadlineEmptyBodyException extends EmptyBodyException {
    DeadlineEmptyBodyException() {}
    @Override
    public String toString() {
        return "☹ OOPS!!! The description of a deadline cannot be empty.";
    }
}