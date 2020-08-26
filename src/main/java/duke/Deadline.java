package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private LocalDate date;
    private Date time = null;
    SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");

    /**
     * Creates an instance of a deadline task.
     * @param description  Contents of the deadline task in the format "deadline XXX /by yyyy-mm-dd HH:mm".
     * @throws DukeException  If format of date and/or time given is not yyyy-mm-dd HH:mm.
     */
    public Deadline(String description) throws DukeException {
        super(description.split("/by ")[0]);
        try {
            String[] dateAndTime = description.split("/by ")[1].split(" ");
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
     * Returns formatted date and time string of a deadline task.
     * @return Formatted date and time string of the deadline task.
     */
    public String getDateAndTimeBracket() {
        if (this.time == null) {
            return String.format("(by: %s)", this.date.format(dateFormatter));
        } else {
            return String.format("(by: %s, %s)", this.date.format(dateFormatter), timeFormatter.format(this.time));
        }
    }
    
    /**
     * Returns String representation of a deadline task.
     * @return String representation of a deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s %s",
                this.getStatusIcon(), this.description, this.getDateAndTimeBracket());
    }
}
