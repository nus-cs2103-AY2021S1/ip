package main.java.duke;

public class Event extends Task {

    /** Date and time of the Event object */
    protected String at;


    /**
     * Constructor of Event.
     * Initialize content, datetime, and completion status of Event object.
     *
     * @param description  Task content of the Event object.
     * @param at  Date and time of the Event object.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }


    /**
     * Another Constructor of Event.
     * Initialize content, datetime, and completion status of Event object.
     *
     * @param description  Task content of the Event object.
     * @param at  Date and time of the Event object.
     * @param isDone  Completion status of Event object.
     */
    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }


    /**
     * Return String "E" to represent type of object.
     *
     * @return "E" to represent the type of object (which is an Event).
     */
    @Override
    public String getType() {
        return "E";
    }


    /**
     * Return Date and Time of Event object.
     *
     * @return datetime of Event object.
     */
    public String getAt() {
        return this.at;
    }


    /**
     * Return Information of Event object.
     *
     * @return An array of Strings containing type, status, content, and datetime of Event object.
     */
    @Override
    public String[] getInfo() {
        return new String[] {this.getType(), this.isDone(), this.description, this.getAt()};
    }


    /**
     * Return String representation of Event object.
     *
     * @return String representation of Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

}