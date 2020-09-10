import java.time.LocalDateTime;

public class Event extends Task {
    /** Time duration of Event. */
    protected String at;

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
            this.at = at;
            LocalDateTime localStart = Parser.getLocalDateTimeStart(at);
            LocalDateTime localEnd = Parser.getLocalDateTimeEnd(at);

            if (localEnd.isAfter(localStart)) {
                this.start = localStart;
                this.end = localEnd;
            } else {
                throw new DukeException("invalidEventChronology");
            }
        } catch (DukeException e) {
            throw e;
        } catch (Exception e) {
            throw new DukeException("invalidEventDateTime");
        }
    }

    @Override
    public String toString() {
        String strStart = Parser.getStringStart(start);
        String strEnd = Parser.getStringEnd(end);
        String startDay = Parser.getStartDay(start);
        String endDay = Parser.getEndDay(end);
        return "[E]" + super.toString() + " (at: " + startDay + ", " + strStart + " to " + endDay + ", " + strEnd + ")";
    }

    /**
     * Returns the Event task string to be written to the duke.txt storage file.
     *
     * @return Event task string.
     */
    public String toStorageString() {
        return "[E]" + super.toStorageString() + " (at: " + this.at + ")";
    }
}
