package duke;

public class Task implements java.io.Serializable {
    private Boolean isDone; // Whether a task is complete
    private final String name; // The task name

    /**
     * Construct a task
     * @param isDone
     * @param name
     */
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

    /**
     * Show task as a string
     * @return
     */
    @Override
    public String toString() {
        String taskString = String.format("%s,%s", this.showStatus(), name);
        return taskString;
    }

    /**
     * Show the task status
     * @return
     */
    public String showStatus() {
        if (isDone) {
            return "1";
        } else {
            return "0";
        }
    }
}
