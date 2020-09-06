package duke.tasks;

import duke.tool.Storage;
import duke.tool.TaskList;
import duke.ui.Ui;

/**
 * The class to Represent a task.
 */
public class Task {
    protected boolean isDone;
    protected String name;
    private boolean isExit;

    /**
     * Constructs a task.
     *
     * @param name the description of the task.
     * @param isDone the boolean to show whether the task is done or not.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
        this.isExit = false;
    }

    /**
     * Gets the status icon of the status of the task.
     *
     * @return the string of the status.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void setIsExit(boolean b) {
        this.isExit = b;
    }
    /**
     * Marks the task as done.
     *
     * @return the done task.
     */
    public Task markDone() {
        this.isDone = true;
        return this;
    }

    /**
     * Gets the description of the task.
     *
     * @return the name of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns string representation of the task.
     *
     * @return
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.name;
    }

    /**
     * Returns the formatted String representation of the task.
     *
     * @return
     */
    public String fileFormattedString() {
        String doneOrNot = isDone ? "1" : "0";
        return "N | " + doneOrNot + " | " + this.name;
    }

    /**
     * Executes the task.
     *
     * @param taskList
     * @param ui
     * @param storage
     * @return
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return null;
    };
}
