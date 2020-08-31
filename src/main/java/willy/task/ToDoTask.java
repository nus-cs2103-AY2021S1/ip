package willy.task;

/**
 * Basic Task with no additional conditions.
 */
public class ToDoTask extends Task {

    public ToDoTask(String task, TaskSymbol taskType) {
        super(task, taskType);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
