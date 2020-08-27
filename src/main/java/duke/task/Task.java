package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Task {

    protected String description;
    protected boolean isDone;
    protected LocalDate date;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, LocalDate date) {
        this.description = description;
        this.date = date;
    }

    public String getDate() {
        if (date != null) {
            return date.format(DateTimeFormatter.ofPattern("dd MMM uuuu"));
        } else {
            return "";
        }
    }

    public String getSaveDate() {
        if (date != null) {
            return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        } else {
            return "";
        }
    }

    public String getStatusIcon() {
        return (isDone ? "✓" : "✘"); //return tick or X symbols
    }

    public String getStatusLetter() {
        return (isDone ? "y" : "n");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public abstract String createSaveDataLine();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description + " ";
    }
}