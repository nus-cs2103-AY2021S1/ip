public class TodoEmptyBodyException extends EmptyBodyException {
    TodoEmptyBodyException() {}
    @Override
    public String toString() {
        return "☹ OOPS!!! The description of a todo cannot be empty.";
    }
}
