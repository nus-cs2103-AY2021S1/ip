package duke.tasks;

/**
 * Base class for tasks in the chatbot and utilised by the main types of tasks.
 */
public class Task {
    public String description;
    public boolean isDone;
    public int index;
    public final String done = "[\u2713] ";
    public final String start = "[\u2718] ";
    public TaskType type;

    /**
     * Constructor for a new Task, the task is set to incomplete by default.
     *
     * @param description   Activity
     * @param index         Index of task
     */

    public Task(String description, int index) {
        this.description = description;
        this.isDone = false;
        this.index = index;
    }

    /**
     * Constructor for a new Task, with additional customisation for completion status.
     *
     * @param description   Activity
     * @param index         Index of task
     * @param isOver        Completion status of task
     */

    public Task(String description, int index, boolean isOver) {
        this.description = description;
        this.isDone = isOver;
        this.index = index;
    }

    /**
     * Method that returns the text version of task with index.
     *
     * @return String representation of tasks with indexing.
     */

    public String getStatusWithIndex() {
        return String.format("%s. %s%s", index, isDone ? this.done : this.start, this.description);
    }

    /**
     * Default toString() method for tasks.
     *
     * @return String representation of tasks.
     */

    public String toString() {
        return String.format("%s%s", isDone ? this.done : this.start, this.description);
    }
}