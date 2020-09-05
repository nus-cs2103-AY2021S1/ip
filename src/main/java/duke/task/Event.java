package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import duke.command.InvalidCommandException;
import duke.component.Parser;

/**
 * Represents an event task that consists of a description and a datetime as the happening time of the event.
 */
public class Event extends Task {
    private final LocalDateTime atTime;
    private final LocalDateTime[] tentativeSlots;
    private final String tentativeSlotsStr;

    /**
     * Creates an event task.
     * @param description the description of the task
     * @param atTime the string description of the time the event happens at
     * @throws InvalidCommandException if the input time format is not yyyy-MM-dd HH:mm
     */
    public Event(String description, String atTime) throws InvalidCommandException {
        super(description);
        String[] times = atTime.split("/");
        tentativeSlots = new LocalDateTime[times.length];
        try {
            for (int i = 0; i < times.length; i++) {
                tentativeSlots[i] = LocalDateTime.parse(times[i], Parser.DATE_TIME_INPUT_FORMAT);
            }
            if (times.length == 1) {
                this.atTime = tentativeSlots[0];
                tentativeSlotsStr = "";
            } else {
                this.atTime = null;
                tentativeSlotsStr = String.join("/", times);
            }
        } catch (Exception e) {
            throw new InvalidCommandException("Invalid input datetime, please input as yyyy-MM-dd HH:mm.");
        }
    }

    @Override
    public boolean isHappeningOn(LocalDate date) {
        return atTime != null && date.isEqual(atTime.toLocalDate());
    }

    @Override
    public boolean isHappeningToday() {
        return atTime != null && isHappeningOn(LocalDate.now());
    }

    @Override
    public boolean hasHappenedBefore(LocalDate date) {
        return atTime != null && atTime.toLocalDate().isBefore(date);
    }

    @Override
    public boolean hasHappenedBeforeToday() {
        return atTime != null && hasHappenedBefore(LocalDate.now());
    }

    @Override
    public boolean isHappeningAfter(LocalDate date) {
        return atTime != null && atTime.toLocalDate().isAfter(date);
    }

    @Override
    public boolean isHappeningAfterToday() {
        return atTime != null && isHappeningAfter(LocalDate.now());
    }

    @Override
    public boolean isHappeningBetween(LocalDate date1, LocalDate date2) {
        if (atTime != null) {
            LocalDate date = atTime.toLocalDate();
            return !date.isAfter(date2) && !date.isBefore(date1);
        } else {
            return false;
        }
    }

    @Override
    public boolean willHappenInDays(int n) {
        return atTime != null && isHappeningBetween(LocalDate.now(), LocalDate.now().plusDays(n));
    }

    @Override
    public String output() {
        if (atTime != null) {
            return "E" + super.output() + " | At: "
                    + atTime.format(Parser.DATE_TIME_INPUT_FORMAT) + "\n";
        } else {
            return "E" + super.output() + " | At: " + tentativeSlotsStr + "\n";
        }
    }

    @Override
    public String toString() {
        if (atTime != null) {
            return "[E]" + super.toString() + " (at: "
                    + atTime.format(Parser.DATE_TIME_OUTPUT_FORMAT) + ")";
        } else {
            return "[E]" + super.toString() + " (at: " + tentativeSlotsStr + ")";
        }
    }

    /**
     * Checks whether the given object equals this Event task.
     * @param obj the given object to compare
     * @return true if the object is an Event and both the description and atTime equals
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Event) {
            Event o = (Event) obj;
            return description.equals(o.description) && atTime.isEqual(o.atTime);
        } else {
            return false;
        }
    }
}
