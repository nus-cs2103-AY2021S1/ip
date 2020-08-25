package duke;

/**
 * Encapsulates an duke.Event task
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

    /**
     * Overriden toString() method
     * @return custom String
     */
    @Override
    public String toString() {
        return "[EVENT]"
                + " " + super.toString()
                + "(at:" + this.timing + ")";
    }

    @Override
    String toStorageRepresentation() {
        String result;
        result = "duke.Event" + "|";
        result += this.isDone ? "1" : "0";
        result += "|";
        result += this.description;
        result += "|";
        result += this.timing;
        return result;
    }
}
