public class Task {
    private String taskName;
    private boolean taskDone;

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
}
