import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Deadline class as the child class of Task
 * contains the information of deadline such as deadline name
 * and deadline date and time
 */
public class Deadline extends Task implements Serializable {

    protected LocalDateTime deadlineDateTime;

    /**
     * Constructor of Deadline
     *
     * @param deadlineName
     * @param deadlineDateTime
     */
    public Deadline(String deadlineName, LocalDateTime deadlineDateTime) {
        super(deadlineName);
        this.deadlineDateTime = deadlineDateTime;
    }

    /**
     * formats the user input of the date and time
     *
     * @return date and time of the deadline task
     * @throws DateTimeParseException
     */
    public String getDeadlineDateTime() throws DateTimeParseException {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm a", Locale.ENGLISH);
        return deadlineDateTime.format(outputFormat);
    }

    /**
     * shows the deadline description, date and time, as well as the icon
     *
     * @return the string format of deadline
     */

    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + getDescription() + "(by: " + getDeadlineDateTime() + ")";
    }
}
