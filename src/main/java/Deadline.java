import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class that makes a deadline reminder.
 */
public class Deadline extends Task {
    protected String descriptionAfterBy;
    protected LocalDateTime localDate;

    /**
     * Constructs a Deadline with a description and a due date.
     *
     * @param description        The description of this deadline.
     * @param descriptionAfterBy The due date of this deadline.
     * @throws DateTimeParseException If date is not in the right format.
     */
    public Deadline(String description, String descriptionAfterBy) throws DateTimeParseException {
        super(description);
        this.descriptionAfterBy = descriptionAfterBy;
        this.localDate = LocalDateTime.parse(descriptionAfterBy, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    /**
     * Constructs a Deadline with a description and a due date.
     *
     * @return Deadline object information in the format to be saved.
     */
    @Override
    public String writeSaveFormat() {
        return String.format("D | %d | %s | %s | %s",
                isDone ? 1 : 0, description, descriptionAfterBy, hasTag ? tagName : "");
    }

    /**
     * Constructs a Deadline with a description and a due date.
     *
     * @return Deadline information in a string.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + localDate.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a")) + ")";
    }
}
