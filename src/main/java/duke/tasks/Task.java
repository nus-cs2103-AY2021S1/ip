package duke.tasks;

/**
 * Base class for tasks in the chatbot and utilised by the main types of tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected int index;
    protected final String done = "[\u2713] ";
    protected final String start = "[\u2718] ";
    protected TaskType type;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean hasDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public TaskType getType() {
        return type;
    }


    /**
     * Method that returns the text version of task with index.
     *
     * @return String representation of tasks with indexing.
     */

    public String getStatusWithIndex() {
        return String.format("%s. %s", index, toString());
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