
/**
 * Represents a task to do and creates a parent class with child classes Event, ToDo, Deadline
 */
public abstract class Task implements Encodable<Task>{





    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * obtains a tick or cross from a Task
     * @return String [✓] or [✗]
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * marks a task as completed by turning boolean isDone to true
     */
    public void markAsDone() {
        this.isDone = true;

    }

    /**
     * overrides Task String output to be formatted
     * @return String of formatted Task
     */
    @Override
    public String toString() {
        String s = "[" + this.getStatusIcon() + "] " + this.description;
        return s;
    }


}
