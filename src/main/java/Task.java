public class Task {
    protected String taskName;
    protected boolean done;
    protected int taskNumber;

    public Task(String taskName, int taskNumber) {
        this.taskName = taskName;
        this.taskNumber = taskNumber;
        this.done = false;
    }

    protected void setDone() {
        this.done = true;
    }

    public int getTaskNumber() {
        return this.taskNumber;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean checkDone() {
        return this.done;
    }

    @Override
    public String toString() {
        String check;
        if (done == true) {
            check = "✓";
        } else {
            check = "✗";
        }
        return taskNumber + ". [" + check + "] " + taskName;
    }
}