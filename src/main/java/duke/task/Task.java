package duke.task;

/**
 * Task class to represent a task to be manipulated by Duke.
 */
public class Task {
    static final String SYMBOL = "T";
    protected String title;
    protected boolean complete;

    /**
     * Constructor of Task class.
     *
     * @param title Title of task.
     */
    public Task(String title) {
        this.title = title;
        this.complete = false;
    }

    /**
     * Overloads constructor of Task class.
     *
     * @param title      Title of task.
     * @param isComplete Boolean to represent completion status of task.
     */
    public Task(String title, boolean isComplete) {
        this.title = title;
        this.complete = isComplete;
    }

    /**
     * Completes this task.
     */
    public void complete() {
        this.complete = true;
    }

    /**
     * Converts the task to a suitable string that can be saved to the .txt file.
     *
     * @return String describing the task.
     */
    public String getSaveString() {
        int completeSymbol = this.complete ? 1 : 0;
        return String.format("%s|%d|%s", SYMBOL, completeSymbol, this.title);
    }

    /**
     * Overrides the standard toString method.
     *
     * @return A string describing the task.
     */
    @Override
    public String toString() {
        String completeSymbol = this.complete ? "[/]" : "[X]";
        return String.format("[%s]%s %s", SYMBOL, completeSymbol, this.title);
    }
}
