package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Deadline extends Task {
    private final String by;
    private LocalDate localDate;
    private String time;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        
        ArrayList<Object> dateAndTime = dateAndTimeFormatter(by);
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
        return "[D]" + super.toString() + " (by: " + by + ") " + getFormattedBy();
    }
    
    @Override
    public String toSave() {
        return "D | " + getDoneInteger() + " | " + description + " | " + this.by;
    }

    @Override
    public boolean equals(Object d) {
        if (d instanceof Deadline) {
            Deadline deadline = (Deadline) d;
            return this.description.equals(deadline.description) &&
                    this.by.equals(deadline.by) &&
                    this.localDate.equals(deadline.localDate) &&
                    this.time.equals(deadline.time);
        } else {
            return false;
        }
    }

   
}
