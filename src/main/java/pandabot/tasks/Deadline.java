package pandabot.tasks;

import pandabot.exceptions.PandaBotException;

/**
 * The Deadline class represents a Deadline task which needs to be
 * done before a specific time.
 */
public class Deadline extends TimedTask {

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
        super(description, dueBy);
    }

    /**
     * Returns a String representation of the task that is displayed to the user
     *
     * @return a String Representation of the task that is displayed to the user
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }

    /**
     * Returns a String representation of the task for saving to the save file
     *
     * @return a String representation of the task for saving to the save file
     */
    @Override
    public String saveAsText() {
        return "D | " + super.saveAsText() + " | " + time;
    }
}
