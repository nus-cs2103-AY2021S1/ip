package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents a specific task with event details.
 */
public class Event extends Task {
    
    private final String eventTime;
    private LocalDate localDate;
    private String time;

    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
        
        ArrayList<Object> dateAndTime = dateAndTimeFormatter(eventTime);
        localDate = null;
        time = null;
        if (dateAndTime.get(0) != null) {
            localDate = (LocalDate) dateAndTime.get(0);
        }
        if (dateAndTime.get(1) != null) {
            time = (String) dateAndTime.get(1);
        }
    }

    /**
     * Returns formatted data and time if valid date and time was inputted.
     * Date is stored as LocalDate object.
     * 
     * @return date followed by time.
     */
    public String getFormattedBy() {
        String formattedBy = "";
        if (localDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
            formattedBy += localDate.format(formatter);
        }
        if (time != null) {
            formattedBy += localDate != null ? " " + time : time;
        }
        if (formattedBy.length() != 0) {
            formattedBy = "(" + formattedBy + ")";
        }
        return formattedBy;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventTime + ") "+ getFormattedBy();
    }

    @Override
    public String toSave() {
        return "E | " + getDoneInteger() + " | " + description + " | " + this.eventTime;
    }
}
