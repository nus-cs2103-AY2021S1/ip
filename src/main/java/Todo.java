public class Todo extends Task {
    public Todo(String description) {
        super(description, TaskType.TODO);
        Task.totalTasks++;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
