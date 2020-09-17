package cartona.task;

import cartona.exception.CartonaException;

/**
 * The Task class represents a task.
 *
 * @author Jaya Rengam
 */
public abstract class Task {
    protected String name;
    protected boolean isDone;
    protected String type;

    /**
     * Creates a Task object
     *
     * @param name the name of the task
     * @param isDone whether the task has been completed or not
     * @param type the type of the task
     */
    Task(String name, boolean isDone, String type) {
        this.name = name;
        this.isDone = isDone;
        this.type = type;
    }

    /**
     * Sets isDone to true.
     *
     * @throws CartonaException if the task is already done
     */
    public void complete() throws CartonaException {
        if (this.isDone) {
            throw new CartonaException(String.format("Error: Task %s is already done", this.toString()));
        }
        this.isDone = true;
    }

    /**
     * Sets isDone to false.
     *
     * @throws CartonaException if the task is not done
     */
    public void uncomplete() throws CartonaException {
        if (!this.isDone) {
            throw new CartonaException(String.format(("Error: Task %s is not done yet"), this.toString()));
        }

        this.isDone = false;
    }

    /**
     * Returns the name of the Task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the 1-character String denoting the type of the Task.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Gets the formatted string representation of the task
     *
     * @return the formatted string representation of the task
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return String.format("[\u2713] %s", name);
        } else {
            return String.format("[\u2717] %s", name);
        }
    }

    /**
     * Gets a shortened string representation of the task
     *
     * @return the shortened string representation of the task
     */
    public String getAbbreviatedString() {
        int isDoneRep = this.isDone ? 1 : 0;
        return String.format("%s | %d | %s", this.type, isDoneRep, this.name);
    }

    public boolean checkIfDone() {
        return this.isDone;
    }
}
