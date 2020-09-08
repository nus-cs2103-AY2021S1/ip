package pandabot.tasks;

import java.time.format.DateTimeParseException;

import pandabot.exceptions.PandaBotException;
import pandabot.exceptions.PandaBotInsufficientArgumentException;

/**
 * The Deadline class represents a Deadline task which needs to be
 * done before a specific time.
 */
public class Deadline extends Task {
    private String dueBy;

    /**
     * Creates a Deadline object.
     * It can accept both formatted and unformatted due dates.
     * Formatted due dates have to be in the format: dd/MM/yyyy HHmm
     *
     * @param description the description of the Deadline task
     * @param dueBy the due date and time of the Deadline task
     * @throws PandaBotException If the description or due date given is empty
     */
    public Deadline(String description, String dueBy) throws PandaBotException {
        super(description);

        String inputDueBy = dueBy.strip();

        if (inputDueBy.length() == 0) {
            throw new PandaBotInsufficientArgumentException();
        }

        // check if a formatted date and time is given
        try {
            DateAndTime dateTime = new DateAndTime(inputDueBy);
            this.dueBy = dateTime.toString();
        } catch (DateTimeParseException e) {
            // if the input couldn't be parsed, the input is an unformatted dueBy
            this.dueBy = dueBy;
        }
    }

    /**
     * Returns a String representation of the task that is displayed to the user
     *
     * @return a String Representation of the task that is displayed to the user
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueBy + ")";
    }

    /**
     * Returns a String representation of the task for saving to the save file
     *
     * @return a String representation of the task for saving to the save file
     */
    @Override
    public String saveAsText() {
        return "D | " + super.saveAsText() + " | " + dueBy;
    }
}
