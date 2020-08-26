public class Todo extends Task {
    public Todo(String description, TaskType taskType) {
        super (description, taskType);
    }

    public Todo(String description, TaskType taskType, boolean isDone) {
        super (description, taskType, isDone);
    }

    @Override
    public String toString() {
        return "[Todo]" + super.toString();
    }
}
