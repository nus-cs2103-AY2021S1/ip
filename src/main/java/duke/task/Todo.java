package duke.task;

import java.time.LocalDate;

/**
 * Encapsulates a todo task.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, LocalDate doneDate) {
        super(description, doneDate);
    }

    @Override
    public String toData() {
        return "T | " + super.toData();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
