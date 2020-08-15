package task;

import exception.DukeException;

public class Todo extends Task {
    private static String TODO = "[T]";

    Todo(String task, boolean completed) {
        super(task, completed);
    }

    public static Todo createTodo(String[] commands) throws DukeException {
        if (commands.length <= 1) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        return new Todo(commands[1], false);
    }

    @Override
    public String toString() {
        return TODO + toStringSuffix();
    }
}
