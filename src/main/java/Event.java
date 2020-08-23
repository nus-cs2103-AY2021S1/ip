//  Events: tasks that start at a specific time and ends at a specific time
//  e.g., team project meeting on 2/10/2019 2-4pm

import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime dateTime;
    protected String dateTimeStr;

    public Event(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
        this.dateTimeStr = dateTime.format(super.formatter);
    }

    @Override
    public String toString() {
        return String.format("[E]%1$s (at: %2$s)", super.toString(), this.dateTimeStr);
    }
}