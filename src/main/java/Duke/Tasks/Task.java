package Duke.Tasks;

import Duke.Tool.Storage;
import Duke.Tool.TaskList;
import Duke.Ui;

/**
 * The class to Represent a task.
 */
public class Task {
    protected String name;
    protected boolean isDone;
    public boolean isExit;

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
        this.isExit = false;
    }

    /**
     * Get the status icon of the status of the task.
     * @return
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Mark the task as done.
     * @return
     */
    public Task markDone() {
        this.isDone = true;
        return this;
    }

    /**
     * String representation of the task.
     * @return
     */
    public String toString() {
        return "[" + this.getStatusIcon() +"] " + this.name;
    }

    /**
     * Formatted String representation of the task.
     * @return
     */
    public String fileFormattedString() {
        String doneOrNot = isDone ? "1" : "0";
        return "N | " + doneOrNot + " | " + this.name;
    }

    /**
     * Excute the task.
     * @param tasklist
     * @param ui
     * @param storage
     */
    public void excute(TaskList tasklist, Ui ui, Storage storage){};
}
