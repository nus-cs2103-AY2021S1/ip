package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.TemporalAccessor;

import duke.exception.DukeException;

public class Deadline extends Task {
    private LocalDateTime timeBy;

    /**
     * @param desc A Constructor for the Deadline object representing a task with a deadline.
     * @param timeBy The time at which the event is due.
     * @throws DateTimeParseException
     */
    public Deadline(String desc, String timeBy) throws DukeException {
        super(desc);
        try {
            LocalDateTime dateTime;
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("dd/MM/yyyy[ HHmm]").withResolverStyle(ResolverStyle.STRICT);;
            TemporalAccessor temporalAccessor = formatter.parseBest(timeBy, LocalDateTime::from, LocalDate::from);
            if (temporalAccessor instanceof LocalDateTime) {
                this.timeBy = (LocalDateTime) temporalAccessor;
            } else {
                this.timeBy = ((LocalDate) temporalAccessor).atStartOfDay();
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Cannot parse date. Make sure the format is dd/MM/yyyy HHmm (optional time)");
        }
    }

    @Override
    public String toString() {
        String sign = isDone ? "✓" : "✗";
        return "[D][" + sign + "] " + description + " (by: " + getDate() + ")";
    }

    @Override
    public TaskType getType() {
        return TaskType.DEADLINE;
    }

    @Override
    public String getDate() {
        return timeBy.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    public String getDelimiter() {
        return "/by";
    }
}
