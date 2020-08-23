import java.time.LocalDate;

/**
 * Deadline is a type of task with a deadline timing.
 */
public class Deadline extends Task {

    protected LocalDate time;

    /**
     * Constructor of deadline.
     *
     * @param description description of deadline.
     * @param time deadline time.
     */
    Deadline(String description, String time) {
        super(description);
        this.time = Parser.stringToDate(time);
    }

    @Override
    public String toString() {
        String timeStr = Parser.dateToString(this.time);
        return String.format("[D]%s(by: %s)", super.toString(), timeStr);
    }

    @Override
    public String toSaveString() {
        return String.format("%s || deadline || %s || %s", super.toSaveString(), this.description, this.time);
    }
}
