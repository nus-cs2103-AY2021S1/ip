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
    public boolean happenToday() {
        return happenOnDate(LocalDate.now());
    }

    @Override
    public boolean happenBeforeDate(LocalDate date) {
        return atTime.toLocalDate().isBefore(date);
    }

    @Override
    public boolean happenBeforeToday() {
        return happenBeforeDate(LocalDate.now());
    }

    @Override
    public boolean happenAfterDate(LocalDate date) {
        return atTime.toLocalDate().isAfter(date);
    }

    @Override
    public boolean happenAfterToday() {
        return happenAfterDate(LocalDate.now());
    }

    @Override
    public boolean happenBetween(LocalDate date1, LocalDate date2) {
        LocalDate date = atTime.toLocalDate();
        return !date.isAfter(date2) && !date.isBefore(date1);
    }

    @Override
    public boolean happenIn(int n) {
        return happenBetween(LocalDate.now(), LocalDate.now().plusDays(n));
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Event) {
            Event o = (Event) obj;
            return description == o.description && atTime.isEqual(o.atTime);
        } else {
            return false;
        }
    }
}
