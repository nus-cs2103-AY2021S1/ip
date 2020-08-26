package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    private final TaskType TYPE;
    private String description;
    private boolean isDone;
    private LocalDate date;

    public Task(String type, String description, LocalDate... date) {
        this.TYPE = TaskType.valueToType(type);
        this.description = description;
        this.isDone = false;
        this.date = date.length > 0 ? date[0] : null;
    }

    public TaskType getType() {
        return TYPE;
    }

    public String getDescription() {
        return description;
    }

    public boolean getStatus() {
        return isDone;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDone() {
        isDone = true;
    }

    public boolean hasDate() {
        return date != null;
    }

    @Override
    public String toString() {
        String string = "[";
        switch (TYPE) {
            case TODO:
                string += "T][" + getStatusIcon() + "] " + getDescription();
                break;
            case EVENT:
                string += "E][" + getStatusIcon() + "] " + getDescription() +
                " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
                break;
            case DEADLINE:
                string += "D][" + getStatusIcon() + "] " + getDescription() +
                        " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
                break;
        }
        return string;
    }
}