package duke.task;

/**
 * Deadline is a type of Task.
 */

public class Deadline extends Task {

    private String dueDate;

    /** Construct a new Deadline.
     * @param name Name or description of the deadline.
     * @param dueDate The due date of the deadline.
     */
    public Deadline(String name, String dueDate){
        super(name);
        this.dueDate = dueDate;
    }

    /** Override the toString method.
     * @return the description and due date of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }
}
