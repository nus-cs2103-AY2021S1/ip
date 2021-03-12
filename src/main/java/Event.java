/**
 * A task that happens at a stated timing
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Event extends Task{

    protected LocalDate at;

    /**
     * Event constructor
     * @param description details of the task
     * @param at Time that the event will be happening
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * method to change event time to a particular format
     * @return time in "MMM dd yyyy" format
     */
    public String dateTimeFormat(){
        return this.at.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * task to be written as string
     * @return string to be presented in a list
     */
    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + this.dateTimeFormat() + ")";
    }

    /**
     * task to be written in file
     * @return string to be written in a file
     */
    @Override
    public String write(){
            return "E|" + this.getStatusIcon() + "|" + this.description + "|" + this.at;
    }
}
