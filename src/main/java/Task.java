public class Task {
    protected String taskName;
    protected boolean taskDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.taskDone = false;
    }

    public String getName() {
        return this.taskName;
    }

    public void setTaskToBeDone() {
        this.taskDone = true;
    }

    public String getStatusSymbol() {
        if (taskDone) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    @Override
    public String toString() {
        return "[" +
                this.getStatusSymbol() + "] " + taskName;
    }
}
