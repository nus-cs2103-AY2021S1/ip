import java.time.LocalDate;

import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private String eventTime;
    private LocalDate startTime;
    private LocalDate endTime;
    private boolean isInDateFormat;

    Event(String description, String eventTime) {
        super(description);
        int idx = eventTime.indexOf(" to ");
        if (idx != -1) {
            try {
                String startStr = eventTime.substring(0, idx);
                String endStr = eventTime.substring(idx + 4);
                // accepts date in format yyyy-MM-dd
                startTime = LocalDate.parse(startStr);
                endTime = LocalDate.parse(endStr);
                isInDateFormat = true;
            } catch (DateTimeParseException e) {
                isInDateFormat = false;
            }
        }
        if (!isInDateFormat) {
            this.eventTime = eventTime;
        }
    }

    @Override
    public String getTypeIcon() {
        return "[E]";
    }

    @Override
    public String toString() {
        return getTypeIcon() + " " + super.getStatusIcon() + " " + super.description + " (at: " 
                + (isInDateFormat 
                    ? "from " + startTime.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
                        + " to " + endTime.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
                    : eventTime) 
                + ")";
    }
    
    public boolean isOccuringOn(LocalDate cmpDate) {
        if (!isInDateFormat) {
            return false;
        }
        return cmpDate.compareTo(startTime) > 0 && cmpDate.compareTo(endTime) < 0;
    }
}
