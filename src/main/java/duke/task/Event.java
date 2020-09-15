package duke.task;

import duke.io.Parser;

import java.time.LocalDate;

/**
 * Represents a specific task with event details.
 */
public class Event extends Task {
    
    private final String eventTime;
    private LocalDate localDate;
    private String time;
    private DateAndTimeFormatter dateAndTimeFormatter;


    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
        initialiseDate();
    }

    private void initialiseDate() {
        dateAndTimeFormatter = new DateAndTimeFormatter(eventTime);
        localDate = dateAndTimeFormatter.getDate();
        time = dateAndTimeFormatter.getTime();
    }

    @Override
    public boolean isSameDate(String date) {
        Parser parser = new Parser();
        if (localDate != null) {
            LocalDate inputDate = parser.checkDate(date);
            return localDate.equals(inputDate);
        }
        return false;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventTime + ") "+ dateAndTimeFormatter.getFormattedBy(localDate, time);
    }

    @Override
    public String toSave() {
        return "E | " + getDoneInteger() + " | " + description + " | " + this.eventTime;
    }
}
