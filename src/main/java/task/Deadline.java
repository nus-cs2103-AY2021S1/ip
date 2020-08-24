package task;

import exception.EmptyTaskException;
import exception.MissingDateException;

public class Deadline extends Task {
    String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    public static Deadline create(String task)
            throws EmptyTaskException, MissingDateException {
        if (task.length() <= 9) throw new EmptyTaskException("deadline");
        String[] taskInfo = task.substring(9).split(" /by ", 2);
        if (taskInfo.length < 2) throw new MissingDateException();
        return new Deadline(taskInfo[0], taskInfo[1]);
    }

    @Override
    public String print() {
        return "D | "
                + (isDone ? 1 : 0) + " | "
                + this.description + " | "
                + this.dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }
}
