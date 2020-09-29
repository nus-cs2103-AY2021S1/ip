package duke.task;

import java.time.LocalDate;

/**
 * Represents a task added by the user, which may be done or not done.
 */
public abstract class Task {
    private String title;
    private boolean isDone;

    /**
     * Class constructor.
     *
     * @param title the content of the task
     */
    public Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    /**
     * Class constructor.
     *
     * @param title  the content of the task
     * @param isDone whether or not the task is marked as completed
     */
    public Task(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    /**
     * Marks the task as completed.
     *
     * @return the task
     */
    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    @Override
    public String toString() {
        String status = this.isDone ? "\u2713" : "\u2718";
        return "[" + status + "] " + this.title;
    }

    /**
     * Returns a string representation of the task to be used in saving the task to a file.
     *
     * @return string representation of the task
     */
    public String print() {
        String status = this.isDone ? "1" : "0";
        return status + " | " + this.title;
    }

    /**
     * Checks if the task has the same date as the given date.
     *
     * @param date the date to compare with
     * @return whether the task has the same date as the given date
     */
    public abstract boolean hasSameDate(LocalDate date);

    public boolean containsKeyword(String keyword) {
        return this.title.contains(keyword.trim());
    }

    @Override
    public boolean equals(Object obj) {
        assert obj instanceof Task;
        Task taskObj = (Task) obj;
        return this.title.equals(taskObj.title) && this.isDone == taskObj.isDone;
    }

    public boolean isDuplicate(Task task) {
        return this.title.equals(task.title);
    }
}
