package duke.task;

import duke.parser.DateParser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Optional;

public class Deadline extends Task {
    private String dueTimeStr;
    private LocalDate dueTime;
    private boolean isInDateFormat;

    public Deadline(String description, String dueTime) {
        super(description);
        this.dueTimeStr = dueTime;
        Optional<LocalDate> optDate = DateParser.parse(dueTime);
        if (optDate.isPresent()) {
            this.dueTime = optDate.get();
            isInDateFormat = true;
        } else {
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
    
    @Override
    public String getTime() {
        return dueTimeStr;
    }
}
