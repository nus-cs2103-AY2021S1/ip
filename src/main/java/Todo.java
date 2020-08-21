public class Todo extends Task {
    public Todo(String description) {
        super(description, TaskType.TODO);
        Task.totalTasks++;
    }

    public Todo(String description, int done) {
        super(description, TaskType.TODO, done);
        Task.totalTasks++;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
