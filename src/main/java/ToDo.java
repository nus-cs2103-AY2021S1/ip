/**
 * ToDo is the simplest kind of Task.
 */
public class ToDo extends Task {

    public ToDo(String description, boolean isDone) {
        super(description, TaskType.TODO, isDone);
    }
}
