package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Event extends Task {
    
    private String eventTime;
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
        return "[E]" + super.toString() + "(at: " + eventTime + ") "+ getFormattedBy();    }

    @Override
    public String toSave() {
        return "E | " + getDoneInteger() + " | " + description + " | " + this.eventTime;
    }
}
