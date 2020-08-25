package duke.task;

/**
 * The Task class is the parent class for deadline, event and todos.
 */
public class Task {

    public String description;
    public boolean isDone;

    /**
     * Constructor for new Task.
     * @param description   the description of the deadline task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for new Task.
     * @return a string that symbolises if the task is done
     */
    public String getStatusIcon() {
        return (isDone ? "[o]" : "[x]"); //return tick or X symbols
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * @return 1 or 0 depending on if the task is done.
     */
    public int ifDone() {
        if (this.isDone) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Displays the task.
     * @return the task in String form
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

    /**
     * How the task is to be written.
     * @return the task in String form
     */
    public String splitToString() {
        return  this.getStatusIcon() + "|" + this.description;
    }

}