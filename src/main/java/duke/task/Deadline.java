package duke.task;

import duke.time.TimePoint;

import java.util.HashMap;

/**
 * Class that represents a <code>Task</code> with a deadline.
 */
public class Deadline extends Task {

    /**
     * Deadline of task.
     */
    private TimePoint deadline;

    /**
     * Creates an initialised <code>Deadline</code> with name and deadline.
     *
     * @param name Name of <code>Deadline</code>.
     * @param deadline Deadline of <code>Deadline</code> task.
     */
    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = TimePoint.of(deadline);
    }

    /**
     * Creates a fully detailed <code>Deadline</code>.
     * For creating <code>Deadline</code> from save file.
     *
     * @param name Name of <code>Deadline</code>.
     * @param deadline Deadline of <code>Deadline</code> task.
     * @param isDone Whether <code>Deadline</code> has been done.
     */
    public Deadline(String name, String deadline, boolean isDone) {
        super(name, isDone);
        this.deadline = TimePoint.of(deadline);
    }

    /**
     * Returns String formatted for representation of <code>Deadline</code> for display.
     *
     * @return Formatted String.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.toString() + ")";
    }

    /**
     * Converts <code>Deadline</code> to <code>HashMap</code> representation.
     * Used for further processing to save file string.
     *
     * @return HashMap representation of properties.
     */
    @Override
    public HashMap<String, String> convertToHashMap() {

        HashMap<String, String> dict = super.convertToHashMap();

        dict.put("type", "duke.task.Deadline");
        dict.put("deadline", this.deadline.toSaveString());

        return dict;
    }
}
