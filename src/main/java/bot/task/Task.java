package bot.task;

/**
 * Encapsulates the task to be done by the user.
 */
public class Task {
    protected String name;
    protected boolean isDone;

    /**
     * Constructor.
     *
     * @param name Name of task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Constructor with its completion status specified.
     *
     * @param name Name of task.
     * @param isDone Task is completed.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Changes the state of done to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Changes the state of done to false
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the class in a String form that is suitable to be written to txt file
     *
     * @return String to be written to user's txt file
     */
    public String toFileFormat() {
        String isDone = this.isDone ? "1" : "0";
        return isDone + " | " + name;
    }

    @Override
    public String toString() {
        String symbol = this.isDone ? "\u2713" : "\u2718";
        return "[" + symbol + "] " + this.name;
    }
}
