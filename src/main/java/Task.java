//Adapted from https://nus-cs2103-ay2021s1.github.io/website/schedule/week2/project.html
public class Task {
    private final String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getTaskName() {
        return taskName;
    }

    private String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718"; //return tick or cross symbols
    }

    public void markDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + taskName;
    }
}
