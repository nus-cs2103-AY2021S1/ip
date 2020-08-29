import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        super(desc.split("deadline ")[1].split(" /by ")[0], "D");
        this.timing = desc.split("deadline ")[1].split(" /by ")[1];
        try {
            String[] split = this.timing.split(" ");
            LocalDate date = LocalDate.parse(split[0]);
            String restOfTime = this.timing.split(split[0])[1];
            this.timing = date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + restOfTime;
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
