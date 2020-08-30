public class Event extends Task {
    private String eventDateTimeStart;

    Event(String description, String eventDateTimeStart) {
        super(description);
        this.eventDateTimeStart = eventDateTimeStart;
    }

    Event(String description, boolean isDone, String eventDateTimeStart) {
        super(description, isDone);
        this.eventDateTimeStart = eventDateTimeStart;
    }

    /**
     * Returns the date and time that the event is due to start at.
     * Returned in the format MMM dd yyyy h.mma e.g. Sep 04 2020 4.00pm
     *
     * @return the date and time that the event is due to start at
     */
    public String getEventDateTimeStart() {
        return this.eventDateTimeStart;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getEventDateTimeStart() + ")";
    }
}
