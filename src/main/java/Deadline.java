import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates a deadline task.
 */
public class Deadline extends Task {

    /**
     * Holds the date of the deadline.
     */
    private LocalDate by;

    /**
     * Creates a deadline task.
     *
     * @param name Description of the task.
     * @param by Deadline of the task.
     * @throws DukeException Thrown when invalid date format is used.
     */
    public Deadline(String name, String by) throws DukeException {
        super(name);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException ex) {
            throw new DukeException("Invalid date format. Use yyyy-mm-dd");
        }
    }

    /**
     * Parses a split serialized string from saved data.
     *
     * @param split Serialized string split by the "|" delimiter.
     * @return Deadline task, or null if save file is corrupted.
     */
    public static Deadline parse(String[] split) {
        try {
            Deadline deadline = new Deadline(split[2], split[3]);
            if (split[1].equals("1")) {
                deadline.markDone();
            }
            return deadline;
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
        return "D|" + super.serialize() + "|" + this.by;
    }

    /**
     * Returns the string representation of the deadline.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
