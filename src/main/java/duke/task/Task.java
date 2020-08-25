package duke.task;

/**
 * The Task class encapsulates information and methods pertaining to a Task.
 *
 * @author  Yen Pin Hsuan
 * @version 1.0
 */
public class Task {
    protected boolean isDone;
    protected String details;

    /**
     * Create a Task with the given details.
     * The Task is set as not done.
     * @param details Details of task.
     */
    public Task(String details) {
        this.isDone = false;
        this.details = details;
    }

    /**
     * Create a ToDo with the given details and date.
     * The ToDo is set as done if isDone is true.
     * @param details Details of the todo.
     * @param isDone True if the todo is done.
     */
    public Task(String details, boolean isDone) {
        this.details = details;
        this.isDone = isDone;
    }

    /** Mark the task as done. */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Return a string representation of the Task to be saved in hard disk.
     * @return String representation of the Task.
     */
    public String store() {
        return details;
    }

    /**
     * Return a string representation of the ToDo to be printed.
     * @return String representation of the ToDo.
     */
    @Override
    public String toString() {
        String s = this.isDone ? "[✓] " : "[✗] ";
        return s + this.details;
    }
}
