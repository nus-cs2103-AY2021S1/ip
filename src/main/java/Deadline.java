package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    private String date;
    private LocalDate dateTime;

    public Deadline(String taskName, String date) throws DukeInvalidDateException, DukeInvalidTaskException {
        super(taskName);
        if(!date.equals(null) && !date.equals(" ")){
            this.date = date;
            try{
                this.dateTime = LocalDate.parse(date);
            } catch (DateTimeParseException err) {
                System.out.println("for dates, please input the date in yyyy-mm-dd format");
            }
        } else {
            throw new DukeInvalidDateException();
        }
    }

    public String getDate(){
        if (dateTime == null) {
            return date;
        } else {
            return dateTime.toString();
        }
    }

    @Override
    public String toString() {
        String finished = this.done ? "✓" : "✗";
        String toReturn = dateTime == null
                            ? "[D]" + "[" + finished + "] " + taskName + " (by: " + date +")"
                            : "[D]" + "[" + finished + "] " + taskName + " (by: "
                                + dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +")";
        return toReturn;
    }
}
