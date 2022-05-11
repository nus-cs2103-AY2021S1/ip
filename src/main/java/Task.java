/**
 * Task class is the subclass of Deadline, Event and Todo
 * thus it holds common methods and constructors for these classes.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task with the given description string.
     *
     * @param description the string that describes the task content
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a task with the given description and completion status.
     *
     * @param description the string that describes the task content
     * @param isDone      the boolean value that indicates the completion status of the task
     */
    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the completion status icon : tick or cross.
     *
     * @return the string that represents the completion status
     */
    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718");
    }

    /**
     * Constructs tasks of the same type as the object calling this method
     * with a "completed" status.
     *
     * @return the completed task with same description and type
     */
    public Task markAsDone() {

        if (this instanceof ToDo) {
            return new ToDo(this.description, true);
        } else if (this instanceof Event) {
            return new Event(this.description, ((Event) this).at, true);
        } else if (this instanceof Deadline) {
            return new Deadline(this.description, ((Deadline) this).by, true);
        } else {
            return new Task(this.description, true);
        }

    }

    /**
     * The overridden toString() method to print out the desired task format.
     *
     * @return a string representation of a task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
