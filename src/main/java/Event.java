import java.time.LocalDate;

/**
 * Event is a type of task with a day time.
 */
public class Event extends Task {

    protected LocalDate time;

    /**
     * Constructor of event.
     *
     * @param description description of event.
     * @param time day of the event time.
     */
    Event(String description, String time) {
        super(description);
        this.time = Parser.stringToDate(time);
    }

    @Override
    public String toString() {
        String timeStr = Parser.dateToString(this.time);
        return String.format("[E]%s(at: %s)", super.toString(), timeStr);
    }

    @Override
    public String toSaveString() {
        return String.format("%s || event || %s || %s", super.toSaveString(), this.description, this.time);
    }
}
