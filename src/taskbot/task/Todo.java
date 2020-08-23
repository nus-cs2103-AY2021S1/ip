package taskbot.task;

public class Todo extends Task {
    public Todo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[" + super.getStatusIcon() + "] " + "taskbot.task.Todo: " + super.getTask() + "\n";
    }
}
