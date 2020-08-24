package duke.task;

import duke.exception.ReadFailedException;

import java.time.LocalDate;

import java.util.function.Function;

/**
 * The Task with description, isDone and type.
 */
public class Task {
    /**
     * The Description.
     */
    protected String description;
    /**
     * True if task is done, false otherwise.
     */
    protected boolean isDone;
    /**
     * The Type of task.
     */
    protected final TaskType type;

    /**
     * Instantiates a new Task.
     *
     * @param description the description.
     * @param type        the type of task.
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Instantiates a new Task.
     *
     * @param description the description.
     * @param isDone      true if task is done, false otherwise.
     * @param type        the type of task.
     */
    public Task(String description, boolean isDone, TaskType type) {
        this.description = description;
        this.isDone = isDone;
        this.type = type;
    }

    /**
     * Returns true, if task has a date, false otherwise.
     *
     * @return the boolean.
     */
    public boolean hasDate() {
        return this.type != TaskType.TODO;
    }

    /**
     * Mark a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a new task.
     *
     * @param stringArr the arr that contains strings of arguments to create a new task.
     * @return the task.
     * @throws ReadFailedException If the task type cannot be recognised.
     */
    public static Task createTask(String[] stringArr) throws ReadFailedException {
        Function<String, Boolean> isDone = num -> num.equals("1");
        Function<String, LocalDate> toDate = date -> LocalDate.parse(date.trim());

        switch (stringArr[0]) {
            case "T":
                return new Todo(stringArr[2], isDone.apply(stringArr[0]));
            case "E":
                return new Event(stringArr[2], toDate.apply(stringArr[3]), isDone.apply(stringArr[0]));
            case "D":
                return new Deadline(stringArr[2], toDate.apply(stringArr[3]), isDone.apply(stringArr[0]));
            default:
                throw new ReadFailedException("tasks");
        }
    }

    /**
     * Returns status icon of the task.
     *
     * @return the status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); // return tick or cross symbols
    }

    /**
     * Returns data of the task.
     * Used to store the task.
     *
     * @return the data.
     */
    public String getData() {
        return String.format("%s_%s_%s", type.toString().charAt(0), isDone ? 1 : 0, description);
    }

    /**
     * Returns the string representation of the task.
     *
     * @return the string.
     */
    @Override
    public String toString() {
        return String.format("[%S][%s] %s", type.toString().charAt(0), this.getStatusIcon(), this.description);
    }
}
