package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents an event task
 */
public class Event extends Task {
    private final LocalDate schedule;

    public Event(String name, boolean isDone, LocalDate schedule) {
        super(name, isDone);
        this.schedule = schedule;
    }

    /**
     * Return a string representation of the Event task's schedule in the pattern "MMM d yyyy".
     *
     * @return the string representation of the schedule.
     */
    public String getSchedule() {
        return this.schedule.format(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH));
    }

    @Override
    public Event complete() {
        return new Event(this.getName(), true, this.schedule);
    }

    @Override
    public String format() {
        int isDoneSignal = this.isDoneTask() ? 1 : 0;
        return "E | " + isDoneSignal + " | " + name + " | " + this.getSchedule();
    }

    @Override
    public String toString() {
        String taskString = super.toString();
        return "[E]" + taskString + " (at: " + this.getSchedule() + ")";
    }
}
