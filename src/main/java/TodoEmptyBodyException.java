/**
 * Represents a Todo Empty Body Exception. A <code>Todo EmptyBody
 * Exception</code> object is used when "todo" is called without further
 * information
 */
public class TodoEmptyBodyException extends EmptyBodyException {
    TodoEmptyBodyException() {}
    @Override
    public String toString() {
        return "☹ OOPS!!! The description of a todo cannot be empty.";
    }
}
