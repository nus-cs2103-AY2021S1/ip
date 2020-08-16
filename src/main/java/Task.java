/**
 * Encapsulates details of a Task
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Instantiates a task with a description of it.
     * @param description an explanation of what the task is about
     */
    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Overloaded private constructor to instantiate a task with customised status.
     * @param description
     * @param done
     */
    private Task(String description, boolean done) {
        this.description = description;
        this.isDone = done;
    }

    /**
     * Visualises the status of a task.
     * @return whether or not a task is done
     */
    String getStatusIcon() {
        return ConsoleColors.BLUE.getColor() + "[" + ConsoleColors.RESET.getColor()
            + (isDone
                ? ConsoleColors.GREEN.getColor() + "\u2713" + ConsoleColors.RESET.getColor()
                : ConsoleColors.RED.getColor() + "\u2718" + ConsoleColors.RESET.getColor()) //return tick or X symbols
            + ConsoleColors.BLUE.getColor() + "]" + ConsoleColors.RESET.getColor();
    }

    /**
     * Marks a task as 'done'.
     * @return a task with a status of 'done'
     */
    Task markAsDone() {
        return new Task(this.description, true);
    }

    /**
     * Provides the description of the task
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " "
                + ConsoleColors.BLUE.getColor()
                + this.description
                + ConsoleColors.RESET.getColor();
    }
}
