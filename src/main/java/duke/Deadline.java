package duke;

/**
 * Encapsulates a duke.Deadline item
 */
public class Deadline extends Task {
    String endDate;

    /**
     * Instantiates a duke.Deadline with a description of it.
     * @param description the description of the deadline
     * @param endDate the end date of the deadline
     */
    Deadline(String description, String endDate) {
        super(description);
        this.endDate = endDate;
    }

    /**
     * Overloaded constructor to instantiate a deadline with customised status.
     * @param description description of the deadline
     * @param done status of the deadline
     * @param endDate the end date of the deadline
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

    /**
     * Overriden toString() method
     * @return custom String
     */
    @Override
    public String toString() {
        return "[DEADLINE]"
                + " " + super.toString()
                + "(by:" + this.endDate + ")";
    }

    @Override
    String toStorageRepresentation() {
        String result;
        result = "duke.Deadline" + "|";
        result += this.isDone ? "1" : "0";
        result += "|";
        result += this.description;
        result += "|";
        result += this.endDate;
        return result;
    }
}
