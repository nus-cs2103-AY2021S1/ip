public class Task {
    protected String name;
    protected boolean done;
    protected Duke.TaskType taskType;

    Task(String name, Duke.TaskType taskType) {
        this.name = name;
        this.done = false;
        this.taskType = taskType;
    }

    Task(String name, Duke.TaskType taskType, boolean done) {
        this.name = name;
        this.done = done;
        this.taskType = taskType;
    }

    public void markDone() {
        this.done = true;
    }

    public String appendFile() {
        String doneString = (done == true ? "1" : "0");
        return this.taskType + " | " + doneString + " | " + this.name;
    }

    @Override
    public String toString() {
        String doneString = (done == true ? "✓" : "✗");
        return "[" + doneString + "] " + this.name;
    }
}
