package task;

public class Todo extends Task {
    private static final String TODO_SYMBOL = "T";
    public static final int COMMAND_LENGTH = 1;

    Todo(String description, boolean completed) {
        super(description, completed);
    }

    public static Todo createTodo(String description) {
        return new Todo(description, false);
    }

    @Override
    public String toString() {
        return "[" + TODO_SYMBOL + "]" + toStringSuffix();
    }
}
