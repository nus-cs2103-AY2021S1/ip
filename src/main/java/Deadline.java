
import java.time.format.DateTimeParseException;

/**
 * The {@code Deadline} class extends the {@code Task} class.
 * Stores the date/time of the event as well.
 */
public class Deadline extends Task {

    private String timing;

    /**
     * Initialises a Deadline task and separates the {@code desc}
     * into the Deadline's description and date/time.
     *
     * @param desc Full description of the Deadline inclusive of time/date.
     */
    public Deadline(String desc) {
        super(Task.getDescriptionFromStringInput(desc, TimeConstraintKeyword.DEADLINE_KEYWORD), "D");
        this.timing = Task.getTimeConstraintFromStringInput(desc, TimeConstraintKeyword.DEADLINE_KEYWORD);
        try {
            this.timing = tryFormatDateElseThrow(timing);
        } catch (DateTimeParseException e) {
            // no date
        }
    }

    /**
     * Initialises a Deadline task.
     *
     * @param desc   Description of the Deadline task.
     * @param timing Date/time of the Deadline.
     */
    public Deadline(String desc, String timing) {
        super(desc, "D");
        this.timing = timing;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + timing + ")";
    }

    /**
     * Returns date/time of the Deadline task.
     *
     * @return Date/time of the Deadline.
     */
    @Override
    public String getTiming() {
        return this.timing;
    }
}
