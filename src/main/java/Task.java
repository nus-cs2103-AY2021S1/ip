public class Task {
    private final String taskDescription;
    private boolean isDone = false;

    Task(String description) {
        taskDescription = description;
    }

    void markAsDone() {
       this.isDone = true;
       System.out.println("I've marked this task as done:\n" + toString());
    }

    public String toString() {
        String status = isDone ? "✓" : "✗";
        return "[" + status + "] " + taskDescription;
    }
}
