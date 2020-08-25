package duke.task;

import duke.exception.EmptyTaskException;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public static ToDo create(String task) throws EmptyTaskException {
        if (task.length() <= 5) throw new EmptyTaskException("todo");
        return new ToDo(task.substring(5));
    }

    @Override
    public String print() {
        return "T | "
                + (isDone ? 1 : 0) + " | "
                + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof ToDo) {
            return description.equals(((ToDo) obj).description);
        } else {
            return false;
        }
    }
}
