package duke.task;

/**
 * Deadline class for tasks that have a set deadline.
 */
public class Deadline extends Task {
    public static final String FORMAT = "deadline [^ ]+.* /by [^ ]+.*";
    public static final String SYMBOL = "D";
    private DateTime deadline;

    /**
     * Constructor for Deadline class.
     *
     * @param title    Title of task.
     * @param deadline Deadline of task.
     */
    public Deadline(String title, String deadline, String[] tags) {
        super(title, tags);
        this.deadline = new DateTime(deadline);
    }

    /**
     * Overloads constructor for Deadline class. Used when reading data from .txt file.
     *
     * @param title      Title of task.
     * @param isComplete Boolean to represent completion status of task.
     * @param deadline   Deadline of task.
     */
    public Deadline(String title, boolean isComplete, String deadline, String[] tags) {
        super(title, isComplete, tags);
        this.deadline = new DateTime(deadline);
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
                deadline.getSaveString(), convertTagsToString("/"));
    }

    /**
     * Overrides the standard toString method.
     *
     * @return A String describing the task with deadline.
     */
    @Override
    public String toString() {
        String completeSymbol = this.isComplete ? "[/]" : "[X]";
        return String.format("[%s]%s %s (by: %s) %s", SYMBOL, completeSymbol, title, deadline, convertTagsToString(""));
    }
}
