public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public static void invalidInput() {
        invalidInput("OOPS!!! The description of a todo cannot be empty.");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
