import java.time.LocalDate;

import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String dueTimeStr;
    private LocalDate dueTime;
    private boolean isInDateFormat;

    Deadline(String description, String dueTime) {
        super(description);
        try {
            this.dueTime = LocalDate.parse(dueTime); // accepts date time in a format yyyy-MM-dd
            isInDateFormat = true;
        } catch (DateTimeParseException e) {
            this.dueTimeStr = dueTime;
            isInDateFormat = false;
        }
    }

    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    @Override
    public String toString() {
        return getTypeIcon() + " " + super.getStatusIcon() + " " + super.description + " (by: " 
                + (isInDateFormat 
                        ? dueTime.format(DateTimeFormatter.ofPattern("d MMM yyyy")) 
                        : dueTimeStr)    
                + ")";
    }
    
    public boolean isDueOn(LocalDate cmpDate) {
        if (!isInDateFormat) {
            return false;
        }
        return cmpDate.isEqual(dueTime);
    }
}
