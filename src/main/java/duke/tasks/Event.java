package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected boolean hasTime = true;
    protected LocalDate time;

    /**
     * Constructs an event object, used when user add new task.
     *
     * @param description Title of the deadline.
     * @param time The deadline date.
     */
    public Event(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    /**
     * Constructs an event object, used when read data from data.txt.
     *
     * @param description Title of the event.
     * @param isDone Whether the task is completed.
     * @param time The event date.
     */
    public Event(String description, int isDone, LocalDate time) {
        super(description, isDone);
        this.time = time;
    }

    public LocalDate getTime() {
        return time;
    }

    public boolean getHasTime() {
        return hasTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String timeString = time.format(formatter);
        return super.toString().replace("[", "[E][") + " (at: " + timeString + ")";
    }

    @Override
    public String getData() {
        return "T" + super.getData() + " | " + time;
    }
}
