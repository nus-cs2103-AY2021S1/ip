package Duke.Tasks;

import Duke.Errors.DukeException;
import Duke.Errors.EventException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The event is a subclass of Task and it is used to describe tasks that has to be completed by a specific day and time
 */
public class event extends Task {
    private String dateTime;
    private String end;
    /**
     *
     * @param name super(name) so that it does whatever is mentioned in the parent class
     * @param dateTime assigns this.dayTime to dayTime value
     */
    public event(String name, String dateTime, String end) {
        super(name);
        this.dateTime = dateTime;
        this.end = end;
    }
    public event(String name, boolean done, String dateTime, String end) {
        super(name, done);
        this.dateTime = dateTime;
        this.end = end;
    }
    /**
     *  Overrides the toString methods
     * @return the specific representation for event class as mentioned with [E] indicating that it is a event class
     *      * and also mentions the event.
     */
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.dateTime +  "-" + this.end + ")";
    }
    /**
     * gives a specific string representation for that in the tasks.txt file and overrides that in Task to make
     * it unique to that for event
     * @return the string representation
     */
    public String inputListFormat(){
        return "E" + super.inputListFormat() + " | " + this.dateTime + "-" + this.end;
    }
}