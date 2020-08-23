public class Event extends Task {
    public Date eventDate;
    public Timing eventTime;

    /**
     * Instantiates Event object.
     * @param description Description of event command.
     * @param eventTime Time of event.
     */
    public Event(String description, String eventTime) {
        super(description, TaskType.EVENT);
        this.eventTime = new Timing(eventTime);
        Task.totalTasks++;
    }

    /**
     * Constructor to instantiate Event object.
     * @param description Description of event command.
     * @param eventDate Date of event.
     * @param eventTime Time of event.
     */
    public Event(String description, String eventDate, String eventTime) {
        super(description, TaskType.EVENT);
        this.eventDate = new Date(eventDate);
        this.eventTime = new Timing(eventTime);
        Task.totalTasks++;
    }

    /**
     * Constructor to instantiate Event object.
     * @param boolDone 1 if it is a done command, 0 if it is not a done command.
     * @param description Description of event command.
     * @param eventDate Date of event.
     * @param eventTime Time of event.
     */
    public Event(int boolDone, String description, String eventDate, String eventTime) {
        super(description, TaskType.EVENT, boolDone);
        this.eventDate = new Date(eventDate);
        this.eventTime = new Timing(eventTime);
        Task.totalTasks++;
    }

    /**
     * Gets Date.
     * @return Date of event object.
     */
    public Date getDate() {
        return eventDate;
    }

    /**
     * Gets Timing.
     * @return Timing of event object.
     */
    public Timing getTiming() {
        return eventTime;
    }

    /**
     * Overrides toString method so as to customize output string format.
     * @return String in our desired format.
     */
    @Override
    public String toString() {
        return super.toString() + " (at: " + eventDate.toString() + ", " + eventTime.toString() + ")";
    }
}
