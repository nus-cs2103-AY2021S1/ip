package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
        this.startTime = null;
        this.endTime = null;
    }

    public Event(String description, Boolean isDone, LocalDate date) {
        super(description, isDone);
        this.date = date;
        this.startTime = null;
        this.endTime = null;
    }

    public Event(String description, Boolean isDone, LocalDate date, LocalTime startTime) {
        super(description);
        this.date = date;
        this.isDone = isDone;
        this.startTime = startTime;
        this.endTime = null;
    }

    public Event(String description, Boolean isDone, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(description);
        this.date = date;
        this.isDone = isDone;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getType() {
        return "E";
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public LocalTime getEndTime() {
        return this.endTime;
    }

    @Override
    public String toString() {
        if(this.startTime != null && this.endTime != null) {
            return "[" + this.getType() + "]" + this.getStatusIcon() + " " + this.description + " (at:"
                    + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + " " + this.startTime.format(DateTimeFormatter.ISO_LOCAL_TIME)
                    + " - " + this.endTime.format(DateTimeFormatter.ISO_LOCAL_TIME) + ")";

        } else if (this.startTime != null) {
            return "[" + this.getType() + "]" + this.getStatusIcon() + " " + this.description + " (at:"
                    + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + " " + this.startTime.format(DateTimeFormatter.ISO_LOCAL_TIME) + ")";

        } else {
            return "[" + this.getType() + "]" + this.getStatusIcon() + " " + this.description + " (at:"
                    + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
    }

    @Override
    public Event markAsDone() {
        //int index = taskNum - 1;
        Event newTask = new Event(this.getDescription(), true, this.date, this.startTime, this.endTime);
        return newTask;
    }

}
