package duke;

/**
 * Implements a Deadline Task
 */
public class Deadline extends Task {

    protected DukeDate by;

    /**
     * Constructor
     * @param description String denoting the name of the Deadline
     * @param by String denoting the date of the deadline
     * @throws DukeException
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = new DukeDate(by);
    }

    /**
     * String representation of a Deadline object
     * @return String representation of a Deadline object
     */
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * String representation of Deadline Task in a format to be saved in hard disk
     * @return String representation of Deadline Task in a format to be saved in hard disk
     */
    public String fileFormat() {
        return "D , " + super.fileFormat() + by.getStringDate();
    }
}