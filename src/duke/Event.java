package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate date;
    private LocalTime time;

    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
        this.time = null;
    }

    public Event(String description, Boolean isDone, LocalDate date) {
        super(description, isDone);
        this.date = date;
        this.time = null;
    }

    public Event(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    public String getType() {
        return "E";
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return this.time != null
                ? "[" + this.getType()  + "]" + this.getStatusIcon() + " " + this.description + " (at:"
                + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " " + this.time.format(DateTimeFormatter.ISO_LOCAL_TIME) + ")"

                : "[" + this.getType()  + "]" + this.getStatusIcon() + " " + this.description + " (at:"
                + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public Event markAsDone() {
        //int index = taskNum - 1;
        Event newTask = new Event(this.getDescription(), true, this.date);
        return newTask;
    }
}
