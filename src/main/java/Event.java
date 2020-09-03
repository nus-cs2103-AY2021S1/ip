import java.time.LocalDateTime;

public class Event extends Task {
    /** Start datetime of the Event. */
    protected LocalDateTime start;

    /** End datetime of the Event. */
    protected LocalDateTime end;

    /**
     * Constructs new Event object.
     *
     * @param description Description of the Event task.
     * @param at Duration of the Event.
     * @throws DukeException If Event task format is invalid.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            LocalDateTime localStart = Parser.getLocalDateTimeStart(at);
            LocalDateTime localEnd = Parser.getLocalDateTimeEnd(at);

            if (localEnd.isAfter(localStart)) {
                this.start = localStart;
                this.end = localEnd;
            } else {
                throw new DukeException("invalidEventChronology");
            }
        } catch (Exception e) {
            throw new DukeException("invalidEventDateTime");
        }
    }

    @Override
    public String toString() {
        String strStart = Parser.getStringStart(start);
        String strEnd = Parser.getStringEnd(start);
        String startDay = Parser.getStartDay(start);
        String endDay = Parser.getEndDay(start);
        return "[E]" + super.toString() + " (at: " + startDay + ", " + strStart + " to " + endDay + ", " + strEnd + ")";
    }
}
