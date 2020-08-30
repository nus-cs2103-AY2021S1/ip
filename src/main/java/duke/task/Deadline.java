package duke.task;
import duke.parser.TimeParser;

/**
 * Represents a Deadline and consists of methods related to Deadline Task.
 */
public class Deadline extends Task {

    /**
     * Constructs a Deadline Task.
     *
     * @param deadlineName Name of Deadline duke.task.
     * @param deadlineTime Time of Deadline duke.task.
     */
    public Deadline(String deadlineName, String deadlineTime) {
        super(deadlineName, deadlineTime);
    }

    /**
     * Returns a string of the format required by the storage file.
     *
     * @return String describing the Deadline duke.task.
     */
    @Override
    public String writeToFile() {
        TimeParser timeParser = new TimeParser(localDate, time);
        String formattedTime = timeParser.getFormattedTime();
        return "deadline" + "|" + this.getStatusSymbol() + "|"
                + this.taskName + "|" + formattedTime;
    }

    /**
     * Returns a string of a format to be printed by Duke.
     *
     * @return String describing the Deadline duke.task.
     */
    @Override
    public String toString() {
        TimeParser timeParser = new TimeParser(localDate, time);
        String formattedTime = timeParser.getFormattedTime();
        return "[D]" + super.toString() + " (by: " + formattedTime + ")";
    }
}
