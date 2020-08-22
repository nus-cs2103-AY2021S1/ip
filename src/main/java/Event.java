import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates an event task.
 */
public class Event extends Task {

    /**
     * Holds the date of the event.
     */
    private LocalDate at;

    /**
     * Creates an event task.
     *
     * @param name Name of event.
     * @param at Date of event.
     * @throws DukeException Thrown when invalid date format is used.
     */
    public Event(String name, String at) throws DukeException {
        super(name);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException ex) {
            throw new DukeException("Invalid date format. Use yyyy-mm-dd");
        }
    }

    /**
     * Parses a split serialized string from saved data.
     *
     * @param split Serialized string split by the "|" delimiter.
     * @return Event task, or null if save file is corrupted.
     */
    public static Event parse(String[] split) {
        try {
            Event event = new Event(split[2], split[3]);
            if (split[1].equals("1")) {
                event.markDone();
            }
            return event;
        } catch (DukeException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Serializes the task into a string to be saved.
     *
     * @return Serialized string.
     */
    public String serialize() {
        return "E|" + super.serialize() + "|" + this.at;
    }

    /**
     * Returns the string representation of the event.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
