package duke.task;

import java.time.LocalDate;

/** The task to be put on the list. */
public abstract class Task {

    /** The task description. */
    protected final String task;

    /** The completion status. */
    protected final boolean isDone;

    /**
     * Constructs a @Task.
     *
     * @param task The task description.
     * @param isDone The task's completion status.
     */
    protected Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }

    /**
     * Constructs an uncompleted @Task.
     *
     * @param task The task description.
     */
    public Task(String task){
        this.task = task;
        this.isDone = false;
    }

    /** Marks the task as done. */
    public abstract Task markDone();

    public String getTask() {
        return this.task;
    }

    public abstract LocalDate getDate();

    /**
     * Compares with another object.
     *
     * @param o The object compared.
     * @return True if the object compared is a @Task with the same task description.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Task) {
            Task t = (Task) o;
            return t.task.equals(this.task);
        } else {
            return false;
        }
    }

    /**
     * The format used to display on a list.
     * @return The String format of a task.
     */
    @Override
    public String toString() {
        String symbol = isDone ? "[✓] " : "[✗] ";
        return String.format("%s %s", symbol, task);
    }

    /**
     * The format used for saving.
     * @return The String format used for saving.
     */
    public String saveFormat() {
        String isDoneString = isDone ? "1" : "0";
        return String.format("%s %s ", isDoneString, task);
    }
}