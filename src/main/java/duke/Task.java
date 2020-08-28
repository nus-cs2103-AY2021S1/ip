package duke;

import java.time.LocalDate;

public abstract class Task {
    private String description;
    private boolean isDone;
    private LocalDate date;

    public Task (String description, boolean isDone, LocalDate date) {
        this.description = description;
        this.isDone = isDone;
        this.date = date;
    }

    public Task (String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDate() {
        return date.toString();
    }

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
