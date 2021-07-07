package duke.tasks;

import duke.DukeException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <code>Event</code> inherits from <code>Task</code>
 * and is used to represent an event.
 */
public class Event extends Task implements Comparable<Event> {
    private Date start;
    private Date end;

    /**
     * Constructor to create an event object.
     * @param name the name of the event
     * @param start the start time of the event in the format "dd MM yyyy HH:mm"
     * @param end the end time of the event in the format "dd MM yyyy HH:mm"
     * @throws DukeException if the format of the given dates are wrong
     */
    public Event(String name, String start, String end) throws DukeException {
        super(name);
        SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy HH:mm");
        try {        
            this.start = formatter.parse(start);
            this.end = formatter.parse(end);
        } catch (ParseException e) {
            throw new DukeException("Date format is invalid!");
        }
    }

    /**
     * Returns a comma seperated <code>String</code> containing the relevant information
     * about this event. This information will then be saved in a text file.
     * @return the comma seperated text of this event
     */
    @Override
    public String saveText() {
        String completeStatus = super.isCompleted() ? "1" : "0";
        DateFormat dateFormat = new SimpleDateFormat("dd MM yyyy HH:mm");
        return "E," + completeStatus + "," + super.getName() + "," + dateFormat.format(start) + "," + dateFormat.format(end);
    }

    /**
     * <code>String</code> represenation of this event object.
     * @return the string representation this object
     */
    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return String.format("[E]%s (start: %s, end: %s)", super.toString(), dateFormat.format(start), dateFormat.format(end));
    }

    /**
     * Compares with another event. Both are equal if they have the same name and start date.
     * Else, they will be compared according to their start date.
     * @param other the other event to be compared with.
     * @return a value 0 if both events are equal;
     * a value greater than 0 if this Event is later than the Event argument;
     * a value less than 0 if this Event is before the Event argument.
     */
    @Override
    public int compareTo(Event other) {
        boolean sameName = getName().equals(other.getName());
        boolean sameDate = start.compareTo(other.start) == 0;
        if (sameName && sameDate) {
            return 0;
        } else {
            return start.compareTo(other.start);
        }
    }
}
