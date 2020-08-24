package duke;

/**
 * Represents a deadline containing a description.
 * Inherits from abstract class TimedTask.
 */
public class Deadline extends TimedTask {

    /**
     * Create a new Deadline instance.
     *
     * @param description describes the deadline.
     * @param datetime contains date and time info.
     */
    public Deadline(String description, String datetime) {
        super(description, datetime);
    }

    /**
     * Returns a text representation of object.
     * This is for purpose of storage in .txt file.
     *
     * @return String of .txt format
     */
    @Override
    protected String textFormat() {
        return "deadline, " + super.textFormat() + "/by" + this.datetime.format(Deadline.inputFormatter);
    }

    /**
     * Returns a string representation of object.
     *
     * @return String object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + super.datetimeString() + ")";
    }
}
