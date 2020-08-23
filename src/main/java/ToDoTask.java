public class ToDoTask extends Task {

    public ToDoTask(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[T] [" + getStatusIcon() + "] " + taskDescription;
    }
}
