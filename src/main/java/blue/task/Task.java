package blue.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.function.Function;

import blue.exception.ReadFailedException;

/**
 * The Task with description, isDone and type.
 */
public class Task {
    /**
     * The constant CROSS_ICON.
     */
    protected static final String CROSS_ICON = "\u2718";
    /**
     * The constant TICK_ICON.
     */
    protected static final String TICK_ICON = "\u2713";
    /**
     * The Type of task.
     */
    protected final TaskType type;
    /**
     * The Description.
     */
    protected String description;
    /**
     * True if task is done, false otherwise.
     */
    protected boolean isDone;

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
     * Returns true if the task is a todo or deadline and
     * the date of task is equal to the date, false otherwise.
     *
     * @param task the task.
     * @param date the date.
     * @return the boolean.
     */
    public static boolean isDateEqual(Task task, LocalDate date) {
        if (!task.hasDate()) {
            return false;
        }
        switch (task.type) {
        case DEADLINE:
            return ((Deadline) task).isDateEqual(date);
        case EVENT:
            return ((Event) task).isDateEqual(date);
        default:
            return false;
        }
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
     * Returns a new task.
     *
     * @param stringArr the arr that contains strings of arguments to create a new task.
     * @return the task.
     * @throws ReadFailedException If the task type cannot be recognised.
     */
    public static Task createTask(String[] stringArr) throws ReadFailedException {
        Function<String, Boolean> isDone = num -> num.equals("1");
        Function<String, LocalDate> toDate = date -> LocalDate.parse(date.trim());
        Function<String, LocalTime> toTime = time -> LocalTime.parse(time.trim());

        switch (stringArr[0]) {
        case "T":
            return new Todo(stringArr[2], isDone.apply(stringArr[0]));
        case "E":
            return new Event(stringArr[2], toDate.apply(stringArr[3]),
                    toTime.apply(stringArr[4]), toTime.apply(stringArr[5]), isDone.apply(stringArr[0]));
        case "D":
            return new Deadline(stringArr[2], toDate.apply(stringArr[3]), isDone.apply(stringArr[0]));
        default:
            throw new ReadFailedException("tasks");
        }
    }

    /**
     * Returns true if the description of the task contains the query string, false otherwise.
     *
     * @param string the query string.
     * @return the boolean.
     */
    public boolean containsDescription(String string) {
        return this.description.contains(string);
    }

    /**
     * Mark a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns data of the task.
     * Used to store the task.
     *
     * @return the data.
     */
    public String getData() {
        return String.format("%s_%s_%s", this.type.toString().charAt(0), this.isDone ? 1 : 0,
                this.description);
    }

    /**
     * Returns the string representation of the task.
     *
     * @return the string.
     */
    @Override
    public String toString() {
        return String.format("[%S][%s] %s", this.type.toString().charAt(0), this.getStatusIcon(),
                this.description);
    }

    /**
     * Returns status icon of the task.
     *
     * @return the status icon.
     */
    public String getStatusIcon() {
        return (this.isDone ? TICK_ICON : CROSS_ICON); // return tick or cross symbols
    }
}
