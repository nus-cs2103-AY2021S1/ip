package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone, TaskType.TODO);
    }
}
