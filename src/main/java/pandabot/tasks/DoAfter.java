package pandabot.tasks;

import pandabot.exceptions.PandaBotException;

/**
 * The DoAfter class represents a DoAfter task which needs to be
 * done after a specific time or task.
 */
public class DoAfter extends TimedTask {

    /**
     * Creates a DoAfter object.
     * It can accept both formatted and unformatted times.
     * Formatted time have to be in the format: dd/MM/yyyy HHmm
     *
     * @param description the description of the DoAfter task
     * @param after the time or task that the DoAfter task has to be done after
     * @throws PandaBotException if the description or time given is empty
     */
    public DoAfter(String description, String after) throws PandaBotException {
        super(description, after);
    }

    /**
     * Returns a String representation of the task that is displayed to the user
     *
     * @return a String Representation of the task that is displayed to the user
     */
    @Override
    public String toString() {
        return "[A]" + super.toString() + " (after: " + time + ")";
    }

    /**
     * Returns a String representation of the task for saving to the save file
     *
     * @return a String representation of the task for saving to the save file
     */
    @Override
    public String saveAsText() {
        return "A | " + super.saveAsText() + " | " + time;
    }
}
