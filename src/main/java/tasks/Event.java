package tasks;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <code>tasks.Event</code> inherits from <code>tasks.Task</code>
 * and is used to represent an event.
 */
class Event extends Task {
    private Date start;
    private Date end;

    /**
     * Constructor to create an event object.
     * @param name the name of the event
     * @param start the start time of the event in the format "dd MM yyyy HH:mm"
     * @param end the end time of the event in the format "dd MM yyyy HH:mm"
     * @throws DukeException if the format of the given dates are wrong
     */
    Event(String name, String start, String end) throws DukeException {
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
;        return "E," + completeStatus + "," + super.getName() + "," + dateFormat.format(start) + "," + dateFormat.format(end);
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
}
