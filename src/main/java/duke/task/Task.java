package duke.task;

/**
 * Parent Task class with multiple sub-classes determining the specific type of Task that the user is adding
 * to Duke's TaskList.
 */
public class Task {
    /** Details of the Task */
    protected String description;
    /** Whether or not the Task is completed */
    protected boolean isDone;

    /**
     * Constructor to create a new Task object.
     *
     * @param description the details of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor to use when reading data from the hard disk upon Duke start up.
     *
     * @param description is the details of the task.
     * @param isDone determines whether the task is marked as completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }


    public String getStatusIcon() {
        return (isDone ? "1" : "0");
    }

    /**
     * Mark task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * The description of the Task, as well as whether or not the Task is completed.
     *
     * @return <code>String</code> representation of the Task.
     */
    @Override
    public String toString() {
       return this.getStatusIcon() + " | " + this.description;
    }
}
