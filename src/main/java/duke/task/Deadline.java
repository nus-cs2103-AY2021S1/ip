package duke.task;

import duke.io.Parser;

import java.time.LocalDate;

/**
 * Represents a specific task with a deadline.
 */
public class Deadline extends Task {
    private final String by;
    private LocalDate localDate;
    private String time;
    private DateAndTimeFormatter dateAndTimeFormatter;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        initialiseDate();
    }
    
    private void initialiseDate() {
        dateAndTimeFormatter = new DateAndTimeFormatter(by);
        localDate = dateAndTimeFormatter.getDate();
        time = dateAndTimeFormatter.getTime();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ") " + dateAndTimeFormatter.getFormattedBy(localDate,
                time);
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
