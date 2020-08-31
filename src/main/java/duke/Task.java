package duke;

import java.time.LocalDate;

public abstract class Task {
    private String description;
    private boolean isDone;
    private LocalDate date;

    /**
     * Constructs and initializes the attributes of a new Task object.
     * @param description The description of the task.
     * @param isDone The status of the task.
     * @param date The date related to the task.
     */
    public Task (String description, boolean isDone, LocalDate date) {
        this.description = description;
        this.isDone = isDone;
        this.date = date;
    }

    /**
     * Constructs and initializes the attributes of a new Task object.
     * @param description The description of the task.
     * @param isDone The status of the task.
     */
    public Task (String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDate() {
        return date.toString();
    }

    /**
     * Make a task to be completed
     * @return A new task object with the same description and date but is completed
     */
    public Task completeTask() {
        if (this instanceof Deadline) {
            return new Deadline(this.description, true, this.date);
        } else if (this instanceof Event) {
            return new Event(this.description, true, this.date);
        } else {
            return new Todo(this.description, true);
        }
    }

    public String getDateDescription() {
        return (this.date != null
                ? " on "
                + date.getMonth()
                + " "
                + date.getDayOfMonth()
                + " "
                + date.getYear()
                : " ");
    }

    @Override
    public String toString() {
        return (isDone ? "[V]" : "[X]")
                + " "
                + description;
    }
}
