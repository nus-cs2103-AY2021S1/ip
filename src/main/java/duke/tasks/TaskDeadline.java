package duke.tasks;

import static duke.commands.CommandReschedule.VALID_TIME_UNITS;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TaskDeadline extends Task {

    private LocalDateTime deadline;

    /**
     * Constructor for Deadline Task.
     * @param description Description of deadline.
     * @param deadline Date and time of deadline.
     */
    public TaskDeadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String[] getSaveData() {
        return new String[] {"D", isDone ? "1" : "0", description,
                String.format("%s", deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd Hmm"))) };
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(),
                description, deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy',' Hmm'hrs'")));
    }

    /**
     * Postpones the deadline.
     * @param amount amount of timeUnit to add.
     * @param timeUnit minutes, hours, days, months, years.
     */
    @Override
    public void postpone(int amount, ChronoUnit timeUnit) {
        assert VALID_TIME_UNITS.contains(timeUnit) : "invalid time unit provided";
        deadline = deadline.plus(amount, timeUnit);
    }

    /**
     * Advances the deadline.
     * @param amount amount of timeUnit to add.
     * @param timeUnit minutes, hours, days, months, years.
     */
    @Override
    public void advance(int amount, ChronoUnit timeUnit) {
        assert VALID_TIME_UNITS.contains(timeUnit) : "invalid time unit provided";
        deadline.minus(amount, timeUnit);
    }
}
