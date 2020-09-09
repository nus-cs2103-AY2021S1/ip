package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents deadline task of user.
 */
public class Deadline extends Task {

    /**
     * Creates Deadline object.
     * @param description Description of the Deadline object.
     * @param by string representation of deadline of Deadline object.
     */
    public Deadline(String description, String by) {
        super(description);
        this.date = LocalDate.parse(by);
        this.dateString = by;
    }

    /**
     * Returns string representation of Deadline object to be stored in file.
     * @return string representation of Deadline object for file storage.
     */
    @Override
    public String convertToFileString() {
        String doneInteger = isDone ? "1" : "0";
        return "D | " + doneInteger + " | " + this.description + " | " + this.dateString;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
