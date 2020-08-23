package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDate date;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone
                ? "✓"
                : "✘";
    }

    public String getDescription() { return this.description; }

    public LocalDate getDate() {
        return this.date;
    }

    public String printDate() {
        return this.date.getDayOfWeek() + ", " + this.date.getMonth() + " " +
                this.date.getDayOfMonth() + " " + this.date.getYear();
    }

    public void finishTask() { isDone = true; }

    public String saveData() {
        return getStatusIcon() + " > " + this.description;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " > " + this.description;
    }


}
