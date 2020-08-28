package duke.Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Event class is a children object of the Task class
 * with the additional abilities to
 * returns date of the Event
 * returns original command of the Event
 */
public class Event extends Task {

    protected String at;

    /**
     * Creates a Event Object
     * @param description
     * @param at
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Creates a Event Object
     * @param description
     * @param at
     * @param isDone
     */
    public Event(String description, String at, boolean isDone){
        super(description, isDone);
        this.at = at;
    }


    /**
     * Returns the date of the Event
     * @return date
     */
    public String getTime(){

        return this.at;
    }


    /**
     * Returns the original command used to create the Event
     * @return original command
     */
    @Override
    public String getOriginal() {

        return "event " + getTask() + " /at " + getTime();
    }

    /**
     * Returns the date in the form of dd MMM yyyy HHmm
     * @return due date of the Event
     */
    private String getEventDate() {
        String[] dateList = this.at.split(" ",2);
        LocalDate eventDate = LocalDate.parse(dateList[0]);
        DateTimeFormatter FormatDate = DateTimeFormatter.ofPattern("dd MMM yyyy");

        if(dateList.length > 1){
            String eventTime = dateList[1];
            return eventDate.format(FormatDate) + " " + eventTime;
        }

        else{
            return eventDate.format(FormatDate);
        }


    }

    /**
     * Returns the Event with StatusIcon
     * @return Event with StatusIcon
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getEventDate() + ")";
    }
}
