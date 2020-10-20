package task;

import data.DateManager;

import java.util.Date;
import java.util.Optional;

/**
 * Deadline object is a subclass of Task object. It contains a deadline String,
 * data.DateManager to process the deadline and an Optional to store a Date object
 * if deadline is of a valid format.
 *
 * @author Hakiem Rasid
 *
 */
public class Deadline extends Task {

    private final String deadline;
    private final Optional<Date> optDate;
    private final DateManager dateManager;

    /**
     * Constructor for Deadline object.
     *
     * @param name Description of task.
     * @param deadline Description of deadline for this task.
     */
    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
        this.dateManager = new DateManager();
        this.optDate = dateManager.getDate(deadline);
    }

    /**
     * Returns Deadline for this task to be completed.
     *
     * @return Deadline as a String.
     */
    public String getDeadline() {
        return this.deadline;
    }

    /**
     * Prints a String representation of Deadline object and processes validity of deadline
     * format to determine format of output.
     * Clearly labels the Deadline object to be easily distinguishable from other
     * Task objects.
     *
     * @return String representation of Deadline.
     */
    @Override
    public String printTask() {
        StringBuilder sb = new StringBuilder();
        sb.append("[D]");
        sb.append(super.printTask());
        if (!optDate.isPresent()) {
            sb.append(" (by: " + this.deadline + ")");
        } else {
            sb.append(" (by: " + dateManager.getDateAsString(deadline) + ")");
        }

        return sb.toString();
    }

}