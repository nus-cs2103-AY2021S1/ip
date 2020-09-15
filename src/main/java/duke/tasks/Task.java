package duke.tasks;

import java.time.LocalDate;

public class Task {
    protected static int numberOfTasks = 0;
    protected String description;
    protected boolean isDone;
    protected boolean hasTime;

    /**
     * Creates a new Task object which is not done.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numberOfTasks++;
    }

    /**
     * Creates a new Task object with description and isDone status.
     *
     * @param description Description of the task.
     * @param isDone Whether the task is complete.
     */
    public Task(String description, int isDone) {
        this.description = description;
        if (isDone == 0) {
            this.isDone = false;
        } else {
            this.isDone = true;
        }
        numberOfTasks++;
    }

    /**
     * Reduces the count of tasks.
     */
    public static void reduceOneTasks() {
        numberOfTasks--;
    }

    /**
     * Gets the tick or cross sign which indicates is a task is complete.
     *
     * @return a tick or X symbols.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public boolean getHasTime() {
        return hasTime;
    }

    public LocalDate getTime() {
        return null;
    }

    public String getDescription() {
        return description;
    }

    public Boolean matchTime(LocalDate date) {
        return null;
    }
    /**
     * Marks a task as finished.
     *
     * @return a finished task.
     */
    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the message when a task is marked as done.
     *
     * @return mark-as-done Message
     */
    public String markAsDoneMessage() {
        return "Nice!(^∇^) I've marked this task as done:\n"
                + this.toString().replace("\u2718", "\u2713");
    }

    /**
     * Returns the message when a new task is added.
     */
    public String addMessage() {
        return "Got it.(^∇^) I've added this task:\n"
                + this.toString();
    }

    /**
     * Returns the message when a task is deleted.
     */
    public String deleteMessage() {
        return "Got it.(^∇^) I've deleted this task:\n"
                + this.toString();
    }

    /**
     * Returns info about the task in the format for data storage.
     *
     * @return A string.
     */
    public String getData() {
        return " | " + (isDone
                ? "1"
                : "0") + " | " + description;
    }
}
