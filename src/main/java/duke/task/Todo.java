package duke.task;

/**
 * Encapsulates information for the Todo type of <code>Task</code>,
 * which only has a task description and status of done or undone.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String saveToFile() {
        return "T/" + super.saveToFile();
    }
}
