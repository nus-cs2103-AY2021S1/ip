package duke.task;

import duke.io.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents a specific task with a deadline.
 */
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
    
    @Override
    public boolean isSameDate(String date) {
        Parser parser = new Parser();
        if (localDate != null) {
            LocalDate inputDate = parser.checkDate(date);
            return localDate.equals(inputDate);
        }
        return false;
   }
   
}
