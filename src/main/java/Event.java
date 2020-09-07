/**
 * Represents an Event task.
 */
public class Event extends Task {
    private final String at;

    /**
     * Constructor for Event task object.
     * @param name The name of event to attend
     * @param at The due date of the task
     */
    public Event(String name, String at) {
        super(name);
        this.at = at;
    }

    /**
     * Overloaded constructor for Event task object.
     * @param name The name of task to be done
     * @param done The boolean for task completion status
     * @param at The event date
     */
    public Event(String name, boolean done, String at) {
        super(name, done);
        this.at = at;
    }

    /**
     * Getter method for attribute at.
     * @return The attribute at of Event object
     */
    public String getAt() {
        return this.at;
    }

    /**
     * Setter method for setting done attribute to given input.
     * @return New Event task with same parameters but attribute done changed to given input
     */
    @Override
    public Event setDone() {
        return new Event(this.getName(), true, this.at);
    }

    /**
     * Updates the time of given Event task.
     * @param time New updated time
     * @return Event task with updated time
     */
    @Override
    public Event updateTime(String time) {
        return new Event(super.name, super.done, time);
    }

    /**
     * Returns the Event object string.
     * @return The Event object string name
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
