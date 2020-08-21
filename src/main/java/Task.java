public class Task {

    private String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public void setDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[" + "\u2713" + "] " + taskName;
        } else {
            return "[" + "\u2717" + "] " + taskName;
        }
    }

    public String getSaveFormat() {
        String done = isDone ? "1" : "0";
        return done + " | " + taskName;
    }
}
