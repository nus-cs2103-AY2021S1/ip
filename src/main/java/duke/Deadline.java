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
    protected String getTxtFormat() {
        String[] splitTag = super.getTxtFormat().split("#", 2);
        if (splitTag.length > 1) {
            return "deadline, " + splitTag[0] + "/by" + this.dateTime.format(Deadline.INPUT_FORMAT)
                    + " #" + splitTag[1];
        }
        return "deadline, " + splitTag[0] + "/by" + this.dateTime.format(Deadline.INPUT_FORMAT);
    }

    /**
     * Returns a string representation of object.
     *
     * @return String object.
     */
    @Override
    public String toString() {
        String[] splitTag = super.toString().split("#", 2);
        if (splitTag.length > 1) {
            return "[D]" + splitTag[0] + " (by: " + super.getDateTimeString() + ")"
                    + " #" + splitTag[1];
        }
        return "[D]" + splitTag[0] + " (by: " + super.getDateTimeString() + ")";
    }
}
