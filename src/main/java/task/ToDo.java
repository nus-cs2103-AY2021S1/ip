package task;

import exception.EmptyTaskException;

public class ToDo extends Task {
    private ToDo(String description) {
        super(description);
    }

    public static ToDo create(String task) throws EmptyTaskException {
        if (task.length() <= 5) throw new EmptyTaskException("todo");
        return new ToDo(task.substring(5));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
