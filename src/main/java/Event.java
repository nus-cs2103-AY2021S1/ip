import java.time.LocalDate;

import java.time.format.DateTimeFormatter;

import java.util.Optional;

public class Event extends Task {
    private String eventTime;
    private LocalDate startTime;
    private LocalDate endTime;
    private boolean isInDateFormat;

    Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
        isInDateFormat = false;
        int idx = eventTime.indexOf(" to ");
        if (idx != -1) {
            Optional<LocalDate> optStart = DateParser.parse(eventTime.substring(0, idx));
            Optional<LocalDate> optEnd = DateParser.parse(eventTime.substring(idx + 4));
            if (optStart.isPresent() && optEnd.isPresent()) {
                startTime = optStart.get();
                endTime = optEnd.get();
                isInDateFormat = true;
            }
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

    @Override
    public String getTime() {
        return eventTime;
    }
}
