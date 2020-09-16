package duke;

public class Task implements java.io.Serializable {
    private Boolean isDone; // Whether a task is complete
    private final String name; // The task name

    public Task(Boolean isDone, String name) {
        this.isDone = isDone;
        this.name = name;
    }

    public void done() {
        isDone = true;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        String taskString = String.format("%s,%s", this.showStatus(), name);
        return taskString;
    }

    public String showStatus() {
        if (isDone) {
            return "1";
        } else {
            return "0";
        }
    }
}
