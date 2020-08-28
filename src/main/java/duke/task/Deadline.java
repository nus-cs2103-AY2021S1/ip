package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/** Represents an deadline task */
public class Deadline extends Task {
    private final LocalDate schedule;

    /**
     * Initializes an instance of deadline object.
     *
     * @param name The name of the deadline task.
     * @param isDone The boolean indicating if the task is done.
     * @param schedule The deadline of the task.
     */
    public Deadline(String name, boolean isDone, LocalDate schedule) {
        super(name, isDone);
        this.schedule = schedule;
    }

    /**
     * Returns a string representation of the Deadline task's schedule in the pattern "MMM d yyyy".
     *
     * @return The string representation of the schedule.
     */
    public String getSchedule() {
        return this.schedule.format(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH));
    }

    @Override
    public Deadline complete() {
        return new Deadline(this.getName(), true, this.schedule);
    }

    @Override
    public String format() {
        int isDoneSignal = this.isDoneTask() ? 1 : 0;
        return "D | " + isDoneSignal + " | " + name + " | " + this.getSchedule();
    }

    @Override
    public String toString() {
        String taskString = super.toString();
        return "[D]" + taskString + " (by: " + this.getSchedule() + ")";
    }
}
