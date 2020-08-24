package duke.tasks;

import duke.utils.DukeDateTime;

/** Represents a deadline. */
public class Deadline extends Task {

    /** The due date of this object. */
    protected DukeDateTime by;

    /** Constructs a Deadline with a description and a due date.
     *
     * @param description The description of this deadline.
     * @param by The due date of this deadline.
     */
    public Deadline(String description, DukeDateTime by) {
        super(description);
        this.by = by;
    }

    /** Gets the due date of this deadline object.
     *
     * @return The due date of this deadline object.
     */
    public DukeDateTime getBy() {
        return by;
    }

    /** Returns the String representation of this deadline in the format that it should be saved
     * in the file.
     *
     * @return The String representation of this deadline in the appropriate format.
     */
    @Override
    public String toFileString() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description, by);
    }

    /** Returns the String representation of this deadline to be displayed to the user.
     *
     * @return The String representation of this deadline to be displayed to the user.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.toString() + ")";
    }
}
