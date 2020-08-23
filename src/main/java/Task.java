/**
 * Task contains the description of command by user and
 * the state of task, whether it is done or not done.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructor of task, item initialized as not done by default.
     *
     * @param description description of task command.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon depending on done status of task.
     *
     * @return string icon of tick or cross.
     */
    public String getStatusIcon() {
        return (isDone ? "✓" : "✘"); //return tick or X symbols
    }

    /**
     * Marks task status to done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns 1 if status is done, 0 if not done.
     * @return string 1 if status is done, 0 if not done.
     */
    public String toSaveString() {
        if (isDone) {
            return "1";
        } else {
            return "0";
        }
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + this.description;
    }
}
