package duke.task;

import duke.command.InvalidCommandException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime atTime;

    public Event(String description, String atTime) throws InvalidCommandException {
        super(description);
        try {
            this.atTime = LocalDateTime.parse(atTime,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (Exception e) {
            throw new InvalidCommandException("Invalid input datetime, please input as yyyy-MM-dd HH:mm.");
        }
    }

    @Override
    public boolean happenOnDate(LocalDate date) {
        return date.isEqual(atTime.toLocalDate());
    }

    @Override
    public String output() {
        return "E" + super.output() + " | At: " +
                atTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                atTime.format(DateTimeFormatter.ofPattern("hh:mm a   MMM d yyyy")) + ")";
    }
}
