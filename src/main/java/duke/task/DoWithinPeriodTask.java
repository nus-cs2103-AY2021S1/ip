package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DoWithinPeriodTask extends Task {
    protected LocalDate start;
    protected LocalDate end;

    /**
     * Takes in the description, start time, and end time of an event and returns an instance of
     * the corresponding type of task.
     *
     * @param description The description of the event.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */
    public DoWithinPeriodTask(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a representation of the task in terms of the description and state.
     *
     * @return The representation of the task.
     */
    @Override
    public String record() {
        if (this.isCompleted) {
            return "P | 1 | " + description + " | " + start + " | " + end;
        } else {
            return "P | 0 | " + description + " | " + start + " | " + end;
        }
    }

    /**
     * Returns a representation of the task in terms of the description and
     * state for the task to be stored into the record.
     *
     * @return The representation of the task in terms of
     *         the description, the state of completion, and time.
     */
    @Override
    public String toString() {
        return "[P]" + super.toString()
                + " (from: " + start.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to " + end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns the boolean value that describes whether the task is on
     * the specific date.
     *
     * @param localDate The specific date.
     * @return The boolean value that describes whether the task is on
     * the specific date.
     */
    @Override
    public boolean isAt(LocalDate localDate) {
        boolean isAtStart = localDate.isEqual(start);
        boolean isAtEnd = localDate.isEqual(end);
        boolean isAfterStart = localDate.isAfter(start);
        boolean isBeforeEnd = localDate.isBefore(end);
        return (isAfterStart && isBeforeEnd) || isAtStart || isAtEnd;
    }
}
