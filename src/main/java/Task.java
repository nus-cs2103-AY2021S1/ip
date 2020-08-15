public class Task {
    public String taskName;
    public boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        isDone = false;
    }

    public void completed() {
        isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "\u2713" : "\u2718", taskName);
    }

    public static void main(String[] args) {
        Task task = new Task("read book");
        task.completed();
        System.out.println(task);
    }
}
