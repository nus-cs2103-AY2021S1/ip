public class TodoException extends ArrayIndexOutOfBoundsException{
    TodoException(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "☹ OOPS!!! The description of a todo cannot be empty.";
    }
}
