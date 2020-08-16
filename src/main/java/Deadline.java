/**
 * Encapsulates a Deadline item
 */
public class Deadline extends Task {
    String endDate;

    /**
     * Instantiates a Deadline with a description of it.
     * @param description the description of the deadline
     */
    Deadline(String description, String endDate) {
        super(description);
        this.endDate = endDate;
    }

    /**
     * Overloaded constructor to instantiate a deadline with customised status.
     * @param description description of the deadline
     * @param done status of the deadline
     */
    Deadline(String description, boolean done, String endDate) {
        super(description, done);
        this.endDate = endDate;
    }

    /**
     * Marks a deadline as 'done'.
     * @return a deadline that is done
     */
    @Override
    Deadline markAsDone() {
        return new Deadline(this.description, true, this.endDate);
    }

    @Override
    public String toString() {
        return ConsoleColors.CYAN_BACKGROUND.getColor() + "DEADLINE" + ConsoleColors.RESET.getColor()
                + " " + super.toString()
                + " (by: " + this.endDate + ")\n";
    }
}
