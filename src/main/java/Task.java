public class Task {
    protected String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getName() {
        return this.taskName;
    }

    public void setTaskToBeDone() {
        this.isDone = true;
    }

    public String getStatusSymbol() {
        if (isDone) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    public void setWhetherTaskDone(String whetherIsDone) {
         isDone = whetherIsDone.equals("true");
    }

    public String writeToFile() {
        return this.getStatusSymbol() + "|" + this.taskName;
    }

    @Override
    public String toString() {
        return "[" +
                this.getStatusSymbol() + "] " + taskName;
    }
}
