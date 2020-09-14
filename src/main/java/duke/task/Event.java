package duke.task;

/**
 * Event class for tasks that have a set timing.
 */
public class Event extends Task {
    public static final String FORMAT = "event [^ ]+.* /at [^ ]+.*";
    public static final String SYMBOL = "E";
    private DateTime time;

    /**
     * Constructor for Event class.
     *
     * @param title Title of task.
     * @param time  Time of task.
     */
    public Event(String title, String time, String[] tags) {
        super(title, tags);
        this.time = new DateTime(time);
    }

    /**
     * Overloads constructor for Event class.
     *
     * @param title      Title of task.
     * @param isComplete Boolean to represent completion status of task.
     * @param time       Time of task.
     */
    public Event(String title, boolean isComplete, String time, String[] tags) {
        super(title, isComplete, tags);
        this.time = new DateTime(time);
    }

    /**
     * Overrides method of saveString method in Task.
     *
     * @return A string to that can be saved into the .txt file
     */
    @Override
    public String getSaveString() {
        int completeSymbol = this.isComplete ? 1 : 0;
        return String.format("%s|%d|%s|%s|%s", SYMBOL, completeSymbol, title,
                time.getSaveString(), convertTagsToString("/"));
    }

    /**
     * Overrides the standard toString method.
     *
     * @return A String describing the task with deadline.
     */
    @Override
    public String toString() {
        String completeSymbol = this.isComplete ? "[/]" : "[X]";
        return String.format("[%s]%s %s (at: %s) %s", SYMBOL, completeSymbol, title,
                time, convertTagsToString(""));
    }
}
