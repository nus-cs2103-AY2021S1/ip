package duke;

/**
 * The event class to store event information
 *
 * @author  Hope Leong
 * @version 0.1
 * @since   27/8/2020
 */
public class Events extends Task {

    /**
     * Event constructor to initialize a event object with the name and time
     * @param name name of event
     */
    Events(String name, String startDateTime, String endDateTime) throws DukeException {
        super(name, startDateTime, endDateTime);
    }

    /**
     * toString method which converts the object to a String
     * @return String
     */
    @Override
    public String toString() {
        if (super.getDone()) {
            return "[E]" + "[\u2714] " + super.getName() + " (at " + printDateTime() + ")";
        } else {
            return "[E]" + "[\u2718] " + super.getName() + " (at " + printDateTime() + ")";
        }

    }

}
