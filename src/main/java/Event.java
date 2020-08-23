package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private String day;
    private LocalDate dateTime;

    public Event(String taskName, String day) throws DukeInvalidDayException, DukeInvalidTaskException {
        super(taskName);
        if(!day.equals(null) && !day.equals(" ")) {
            this.day = day;
            try{
                this.dateTime = LocalDate.parse(day);
            } catch (DateTimeParseException err) {
                System.out.println("for dates, please input the date in yyyy-mm-dd format");
            }
        } else {
            throw new DukeInvalidDayException();
        }
    }

    public String getDate(){
        if (dateTime != null){
            return dateTime.toString();
        } else {
            return day;
        }
    }

    @Override
    public String toString() {
        String finished = this.done ? "✓" : "✗";
        String toReturn = dateTime == null
                            ? "[E]" + "[" + finished + "] " + taskName + " (at: " + day + ")"
                            : "[E]" + "[" + finished + "] " + taskName + " (at: "
                                + dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        return toReturn;
    }
}
