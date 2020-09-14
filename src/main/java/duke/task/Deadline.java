package duke.task;

/**
 * Represent a Deadline object
 * A <code>Deadline</code> corresponds to a task created using the command
 * "deadline"
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructor of the Deadline Class
     *
     * @param description description of the task
     * @param by date of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor of the Deadline Class
     *
     * @param description description of the task
     * @param by date of the task
     */
    public Deadline(String description, int status, String by) {
        super(description, status);
        this.by = by;
    }

    /**
     * Returns a string to be written inside the text file
     *
     * @param status current status of the task
     * @return string representation of the task
     */
    @Override
    public String saveText(int status) {
        return "D | " + status + " | " + description + " | " + by;
    }

    /**
     * Returns a string representation of a task object
     *
     * @return string representation of a task object
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}
