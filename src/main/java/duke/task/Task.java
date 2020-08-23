package duke.task;

/**
 * Encapsulates a Task.
 * May be a ToDo, Event, or Deadline.
 */

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new task based on the description and marks it as not done.
     * @param description Details of the task.
     */
    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks the event as done.
     * If the event is already completed, indicate to the user.
     */
    public void complete() {
        if (this.isDone) {
            System.out.println("Task is already completed.");
        } else {
            System.out.println("Task completed: ");
        }
        this.isDone = true;
        System.out.println(this.toString());
    }

    /**
     * Expresses the task as a string which is used for storage purposes.
     * @return A string representing the task.
     */
    public String store() {
        return this.isDone + "|" + this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}