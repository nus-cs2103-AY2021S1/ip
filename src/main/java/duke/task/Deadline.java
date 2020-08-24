package duke.task;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Encapsulate the deadline class
 */
public class Deadline extends Task {
    private Date time;
    private SimpleDateFormat formatter;

    /**
     * Creates a new deadline
     * @param desc Description of the deadline
     * @param time Time of the deadline
     */
    public Deadline(String desc, Date time) {
        super(desc);
        this.time = time;
        formatter = new SimpleDateFormat ("MMM dd yyyy hh:mm a");
    }

    /**
     * Returns string representation of the deadline
     * @return String representation
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), formatter.format(time));
    }

    /**
     * Returns string to be saved
     * @return String to be saved
     */
    @Override
    public String toFileString() {
        return "D\n"+super.toFileString()+formatter.format(time) + "\n";
    }
}
