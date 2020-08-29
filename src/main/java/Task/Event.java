package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import exceptions.WrongDateTimeFormatException;

public class Event extends Task {

    /**
     * The time of the event
     **/
    private final LocalDateTime at;

    /**
     * Initializes event task
     *
     * @param name
     * @param isDone
     * @param end
     */
    public Event(String name, boolean isDone, String end) throws WrongDateTimeFormatException {
        super(name, isDone);
        try {
            this.at = LocalDate.parse(end.substring(0, 10)).atTime(
                Integer.parseInt(end.substring(11, 13)),
                Integer.parseInt(end.substring(13))
            );
        } catch (DateTimeParseException e) {
            throw new WrongDateTimeFormatException(
                "☹ OOPS!!! Please enter the deadline time in the right format. (YYYY-MM-DD HHmm)");
        }
    }

    public Event(String name, boolean isDone, LocalDateTime at) {
        super(name, isDone);
        this.at = at;
    }

    /**
     * Set the current event to done
     **/
    @Override
    public Task setToTrue() {
        return new Event(this.name, true, this.at);
    }

    /**
     * Get the type of the current task
     **/
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Get the specific time when the event is going to be held
     **/
    @Override
    public String getEnd() {
        return this.at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Convert the current task to String
     **/
    @Override
    public String toString() {
        return isDone
            ?
            "[E][✓] " + this.getName() + " (by: " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")"
            : "[E][✗] " + this.getName() + " (by: " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) +
            ")";
    }

    /**
     * Override the equals from Object so that it can be used to handle event
     **/
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof Event) {
            Event temp = (Event) o;
            return this.name.equals(temp.name) && (this.isDone == temp.isDone) && this.at.equals(temp.at);
        } else {
            return false;
        }
    }
}
