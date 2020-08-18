public class Task {
    protected String taskName;
    protected boolean done;
    protected String taskType;

    public Task(String taskName, String taskType) {
        this.taskName = taskName;
        this.done = false;
        this.taskType = taskType;
    }

    protected void setDone() {
        this.done = true;
    }


    public String getTaskName() {
        return this.taskName;
    }

    public boolean checkDone() {
        return this.done;
    }

    public String getTaskType() {
        return "[" + this.taskType + "]";
    }

    @Override
    public String toString() {
        String check;
        if (done == true) {
            check = "✓";
        } else {
            check = "✗";
        }
        return "[" + taskType + "][" + check + "] " + taskName;
    }
}