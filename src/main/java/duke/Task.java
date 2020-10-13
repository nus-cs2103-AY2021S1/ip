package duke;

public class Task {
    protected String description;
    protected boolean isDone;
    public static final int DONE = 1;
    public static final int NOT_DONE = 0;

    /**
     * Different types of Task.
     */
    public enum Type {
        TODO, DEADLINE, EVENT
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean status) {
        this.description = description;
        this.isDone = status;
    }

    /**
     * Returns a symbol depending on status of task.
     *
     * @return Tick or cross symbol.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2714" : "\u2718"); //return tick or X symbols
    }

    /**
     * Getter method for description.
     *
     * @return Description of task.
     */
    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    public String toText() {
        return toText("T");
    }

    /**
     * Converts the Task into standard form for ease of saving to data file.
     *
     * @param type Indicates the type of task.
     * @return Description of Task in standard form.
     */
    public String toText(String type) {
        int doneInt = this.isDone ? DONE : NOT_DONE;
        return String.format("%s | %d | %s", type, doneInt, this.description);
    }
}