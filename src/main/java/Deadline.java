package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Deadline class is used to represent the task of a deadline nature.
 * This object contains the task name as well as the day/date of the deadline.
 * Inherits from Task class.
 */

public class Deadline extends Task{
    private String date;
    private LocalDate dateTime;

    /**
     * Initializes a Deadline object
     *
     * @param taskName name or description of task
     * @param date date in which task has to be completed
     * @throws DukeInvalidDateException
     * @throws DukeInvalidTaskException
     */

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

    /**
     * Gets the date of the Deadline
     *
     * @return a String representing the date.
     */

    public String getDate(){
        if (dateTime == null) {
            return date;
        } else {
            return dateTime.toString();
        }
    }

    /**
     * Get a string representation of the object
     *
     * @return a String representing the Deadline object
     */

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
