package duke.task;

//@@author CS2103T-week2-project-task3
//Reused from https://nus-cs2103-ay2021s1.github.io/website/schedule/week2/project.html

/**
 * Represents Task that has a description that can be marked as done.
 */
public abstract class Task {
    /** Description of the task. */
    protected String description;
    /** Represents task as done or not done. */
    protected boolean isDone;

    /**
     * Creates a new Task object with a default not done.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a new Task object.
     * @param description Description of the task.
     * @param isDone Indicates if task is done or not done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Converts isDone boolean into a tick or a cross.
     * @return A tick or a cross depending on isDone.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    //@@author

    /**
     * Converts task into String to be displayed to user
     * @return task as a String
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Marks a task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Converts task into String to be stored in the text file.
     * @return task as a String.
     */
    public String toStoredTextString() {
        String done = (isDone) ? "1" : "0";
        return done + " | " + description;
    }
}