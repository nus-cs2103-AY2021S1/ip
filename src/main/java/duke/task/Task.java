package duke.task;

/**
 * A class to represent a general task that is created by the user
 *
 * @author Roger Lim
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task with the description and isDone at false.
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * Creates a task with the description and isDone specified.
     * @param description
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public boolean contains(String input) {
        return description.contains(input);
    }

    public String getStatusIcon() {
        String sign = isDone ? "\u2713" : "\u2718"; //return tick or X symbols
        return (String.format("[%s]", sign));
    }

    public String getOutput() {
        return String.format("%s%s", getStatusIcon(), this.description);
    }

    public void setDone() {
        isDone = true;
    }

    public String writeToFile() {
        return "-1";
    }
}
