package duke.tasks;

import static duke.commands.CommandReschedule.VALID_TIME_UNITS;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TaskEvent extends Task {

    private LocalDate eventDate;
    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * Constructor for Event Task.
     * @param description
     * @param eventDate
     * @param startTime
     * @param endTime
     */
    public TaskEvent(String description, LocalDate eventDate, LocalTime startTime, LocalTime endTime) {
        super(description);
        this.eventDate = eventDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String[] getSaveData() {
        return new String[] {"E", isDone ? "1" : "0", description,
                String.format("%s %s-%s", eventDate.format(DateTimeFormatter.ISO_LOCAL_DATE),
                        startTime.format(DateTimeFormatter.ofPattern("Hmm")),
                        endTime.format(DateTimeFormatter.ofPattern("Hmm")))};
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", getStatusIcon(), description,
                String.format(
                        eventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                        + ", " + startTime.format(DateTimeFormatter.ofPattern("Hmm'hrs'"))
                        + " - " + endTime.format(DateTimeFormatter.ofPattern("Hmm'hrs'"))
                        ));
    }

    /**
     * Postpones the event.
     * @param amount amount of timeUnit to add.
     * @param timeUnit minutes, hours, days, months, years.
     */
    public void postpone(int amount, ChronoUnit timeUnit) {
        assert VALID_TIME_UNITS.contains(timeUnit) : "invalid time unit provided";
        switch (timeUnit) {
        case MINUTES: case HOURS:
            startTime = startTime.plus(amount, timeUnit);
            endTime = endTime.plus(amount, timeUnit);
            break;
        case DAYS: case MONTHS: case YEARS:
            eventDate = eventDate.plus(amount, timeUnit);
            break;
        default:
        }
    }

    /**
     * Advances the event.
     * @param amount amount of timeUnit to add.
     * @param timeUnit minutes, hours, days, months, years.
     */
    public void advance(int amount, ChronoUnit timeUnit) {
        assert VALID_TIME_UNITS.contains(timeUnit) : "invalid time unit provided";
        switch (timeUnit) {
        case MINUTES: case HOURS:
            startTime = startTime.minus(amount, timeUnit);
            endTime = endTime.minus(amount, timeUnit);
            break;
        case DAYS: case MONTHS: case YEARS:
            eventDate = eventDate.minus(amount, timeUnit);
            break;
        default:
        }
    }
}
