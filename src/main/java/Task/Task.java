package main.java.Task;

import main.java.Ui;

/**
 * Represents a Task that user wishes to add.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs task with the description of the task.
     * @param description description of the task.
     */
     public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs task with the description of the task and status of whether it is completed.
     * @param description  description of the task.
     * @param isDone boolean of whether the task is completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the task complete.
     */
    public void finishTask() {
         Ui.printDoneMessage(this.isDone);
        if (!this.isDone) {
            this.isDone = true;
        }
    }

    /**
     * Returns the tick and cross symbol for a complete and incomplete task respectively.
     * @return unicode character for tick and cross
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     *  Returns the type of task.
     * @return type of task.
     */
    public String getTypeOfTask() {
        return "todo";
    }

    @Override
    public String toString() {
        return "[T] [" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns the representation of task to be stored in hard disk.
     * @return String representation of the task for storage.
     */
    public String getStoreRepresentation() {
        String doneStatus = this.isDone ? "D," : "N,";
        return "T," + doneStatus + this.description;
    }
}