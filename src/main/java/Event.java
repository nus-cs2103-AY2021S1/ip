package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Event class is used to represent the task of an event nature.
 * This object contains the task name as well as the day/date of the event.
 * Inherits from Task class.
 */

public class Event extends Task {
    private String day;
    private LocalDate dateTime;

    /**
     * Initializes the Event object
     *
     * @param taskName name or description of task
     * @param day day in which task has to be completed
     * @throws DukeInvalidDayException
     * @throws DukeInvalidTaskException
     */

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


    /**
     * Gets the date of the Event
     *
     * @return a String representing the day.
     */
    public String getDate(){
        if (dateTime != null){
            return dateTime.toString();
        } else {
            return day;
        }
    }
    /**
     * Get a string representation of the object
     *
     * @return a String representing the Event object
     */


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
