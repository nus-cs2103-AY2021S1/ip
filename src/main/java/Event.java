/**
 * Event class represents a Task that is an Event.
 * Extends from the Task class.
 */
public class Event extends TimedTask {

    /**
     * Constructor that creates an Event object
     * @param name the name of the Event.
     * @param dateTime the date amd time of the Event in DD-MM-YYYY HHMM format.
     */
    Event(String name, String dateTime) {
        super(name, dateTime);
        taskType = "E";
    }

    /**
     * Overloaded constructor that creates an Event object with a specified
     * completion status.
     * @param name the name of the Event.
     * @param isDone the completion status of the Event.
     * @param dateTime the date and time of the Event.
     */
    Event(String name, Boolean isDone, String dateTime) {
        super(name, isDone, dateTime);
        taskType = "E";
    }
    
    @Override
    public String toString() {
        return String.format("[%s]%s", taskType, super.toString());
    }
}
