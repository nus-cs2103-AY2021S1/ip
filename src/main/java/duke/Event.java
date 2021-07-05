package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents an event.
 */
public class Event extends Task {
    private Date time = null;
    private SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");

    /**
     * Creates an instance of a deadline task.
     * @param description  Contents of the event task in the format "deadline XXX /by yyyy-mm-dd HH:mm".
     * @throws DukeException  If format of date and/or time given is not yyyy-mm-dd HH:mm.
     */
    public Event(String description) throws DukeException {
        super(description.split("/on ")[0]);
        try {
            String[] dateAndTime = description.split("/on ")[1].split(" ");
            this.date = LocalDate.parse(dateAndTime[0]);
            if (dateAndTime.length == 2) {
                SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm");
                this.time = parseFormat.parse(dateAndTime[1]);
            }
        } catch (Exception e) {
            throw new DukeException("Please input a valid date and time in the format yyyy-mm-dd HH:MM");
        }
    }
    
    /**
     * Returns formatted date and time string of an event task.
     * @return Formatted date and time string of the event task.
     */
    public String getDateAndTimeBracket() {

        if (this.time == null) {
            return String.format("(on: %s)", this.date.format(dateFormatter));
        } else {
            return String.format("(on: %s, %s)", this.date.format(dateFormatter), timeFormatter.format(this.time));
        }
    }

    /**
     * Returns String representation of an event task.
     * @return String representation of an event task.
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s %s",
                this.getStatusIcon(), this.description, this.getDateAndTimeBracket());
    }
}
