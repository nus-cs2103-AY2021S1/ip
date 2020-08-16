public class Task {
    String taskName;
    Task(String task) {
        this.taskName = task;
    }

    @Override
    public String toString() {
        return taskName;
    }
}
