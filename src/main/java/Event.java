/**
 * Encapsulates an Event task
 */
public class Event extends Task {
    String timing;

    /**
     * Instantiates an event with a description of it.
     * @param description description of the event
     * @param timing the timing of the event
     */
    Event(String description, String timing) {
        super(description);
        this.timing = timing;
    }

    /**
     * Overloaded constructor to instantiate a event with customised status.
     * @param description description of the event
     * @param done status of the event
     * @param timing the end date of the event
     */
    Event(String description, boolean done, String timing) {
        super(description, done);
        this.timing = timing;
    }

    /**
     * Marks a event as 'done'.
     * @return a event that is done
     */
    @Override
    Event markAsDone() {
        return new Event(this.description, true, this.timing);
    }

    @Override
    public String toString() {
        return ConsoleColors.PURPLE_BACKGROUND.getColor() + "EVENT" + ConsoleColors.RESET.getColor()
                + " " + super.toString()
                + " (at:" + this.timing + ")";
    }
}
