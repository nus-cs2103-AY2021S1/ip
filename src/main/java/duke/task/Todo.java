package duke.task;

/**
 * Represents a todo task that extends from the Task class, which consists of a description.
 */
public class Todo extends Task {

    public Todo(String taskName) {
        super(taskName, "T");
    }
}
