public class Task {
    private String taskName;
    private boolean isDone;
    
    private static String TICK = "\u2713";
    private static String cross = "\u2718";
    
    public static int totalTasks = 0;

    public Task(String taskName) {
        totalTasks++;
        this.taskName = taskName;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public String toTaskData() {
        return (this.isDone ? "1" : "0") + " ; " + this.taskName;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? TICK : cross) +
                "] " + taskName;
    }
}
