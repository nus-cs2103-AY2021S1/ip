package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private String deadline;
    private LocalDate date;
    private LocalTime time;
    
    public Deadline(String description, boolean isCompleted, String deadline) {
        super(description,isCompleted);
        this.deadline = deadline;

        this.date = parseDate(deadline);
        this.time = parseTime(deadline);

    }
    public String getDeadline() {
        return this.deadline;
    }

    public String getTaskSymbol() {
        return "[D]";
    }

    public String storeFormat() {
        return String.format("%s %s %s %s %s",this.getTaskSymbol(),this.isDone(),this.description,this.deadline,this.getTag());
    }
    
    public String toString () {
        return String.format("%s (by: %s %s)",this.description,date.format(DateTimeFormatter.ofPattern("MMM d yyyy")),time.toString());
    }
    
    public LocalDate parseDate(String deadline) {
       return  LocalDate.parse(deadline.split("\\s+")[0]);
    }
    
    public LocalTime parseTime(String deadline) {
        return LocalTime.parse(deadline.split("\\s+")[1]);
    }
}
