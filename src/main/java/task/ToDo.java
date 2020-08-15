package task;

public class Todo extends Task {
    private static String TODO = "[T]";

    Todo(String task, boolean completed) {
        super(task, completed);
    }

    public static Todo createTodo(String[] commands) {
        return new Todo(commands[1], false);
    }

    @Override
    public String toString() {
        return TODO + toStringSuffix();
    }
}
