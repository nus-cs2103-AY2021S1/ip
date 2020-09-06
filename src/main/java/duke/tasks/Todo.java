package duke.tasks;

/**
 * Subclass of task
 */
public class Todo extends Task {

    /**
     * Constructor for Todo class
     * @param description description of the task
     * @param isDone indicates whether the task has been completed or not
     */
    public Todo (String description, boolean isDone) {
        super (description, "Todo", isDone);
    }

    @Override
    public String toString() {
        if (isDone) {
            return String.format ("[T][\u2713] %s", this.description);
        } else {
            return String.format ("[T][\u2717] %s", this.description);
        }
    }
}
