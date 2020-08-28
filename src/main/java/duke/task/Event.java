package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/** Represents an event task */
public class Event extends Task {
    private final LocalDate schedule;

    /**
     * Initializes an event task.
     *
     * @param name The name of the event task.
     * @param isDone The boolean indicating if the task is done.
     * @param schedule The time to do the event.
     */
    public Event(String name, boolean isDone, LocalDate schedule) {
        super(name, isDone);
        this.schedule = schedule;
    }

    /**
     * Returns a string representation of the Event task's schedule in the pattern "MMM d yyyy".
     *
     * @return The string representation of the schedule.
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
