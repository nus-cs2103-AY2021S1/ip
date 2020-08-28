/**
 * Encapsulates the idea of a task, set as abstract
 * to prevent instantiation
 */
package duke.task;

public abstract class Task {

    // String constants for labelling state of task
    protected static final String CROSS = "✗";
    protected static final String CHECK = "✓";

    // instance fields
    protected String desc; // description of task
    protected boolean isDone; // state of task

    // constructors
    Task(String desc) {
        this.desc = desc;
    }
    Task(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
    }

    // accessors for labels, descriptions and status of task
    public String getStateLabel() {
        return isDone ? CHECK : CROSS;
    }
    public String getDesc() {
        return desc;
    }
    public boolean isDone() {
        return isDone;
    }

    // mutator for setting state of task
    public void setTaskAsDone() {
        isDone = true;
    }

    // String representation methods

    /**
     * Converts a task into its database representation:
     * task type | 0-1 completion flag | description
     * @return database representation of task
     */
    public String databaseRep() {
        return String.format("%s | %s", isDone ? 1 : 0, desc);
    }
}
