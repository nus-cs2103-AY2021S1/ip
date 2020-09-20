package duke.tasks;


import duke.exceptions.DukeDateTimeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * The Event class contains the information that is entered by the user into the
 * Duke Chatbot Command Line Interface.
 */
public class Deadline extends TimedTask {
    /**
     * Constructor for Deadline Class without done status
     * To be used for creating new duke.tasks by the end user
     * @param desc the description of the task
     * @param date the date on which the task is due
     * @throws DukeDateTimeException if the fields for the date are not matching autocorrection cases
     */
    private Deadline(String desc, String date) throws DukeDateTimeException {
        super(desc, date);
    }
    /**
     * Constructor for Deadline class with done status
     * To be used by the I/O manager to read duke.tasks and populate the system at runtime.
     * @param desc the description of the task.
     * @param date the date on which the task is due.
     * @param b the Done Status of the Task.
     * @throws DukeDateTimeException if the fields for the date are not matching autocorrection cases
     */
    Deadline(String desc , String date , Boolean b) throws DukeDateTimeException {
        super(desc, date, b);
    }
    /**
     * Static Factory method Creates new Deadlines from user input, and adds additional constraint on user
     * input in order to input a valid new event
     * @param desc Deadline description
     * @param date date or partial date for 
     * @return Deadline created from a validated input date.
     * @throws DukeDateTimeException if the date given by user is before, or is autocorrected before date
     */
    public static Deadline createNewDeadline(String desc, String date) throws DukeDateTimeException {
        try {
            if (NOW.format(FMAT).length() > date.length()) {
                date = date + NOW.format(FMAT).substring(date.length());
            }
            LocalDateTime dateby = LocalDateTime.parse(date, FMAT);
            if (dateby.toLocalDate().isBefore(NOW)){
                throw new DukeDateTimeException("The date you entered "+ dateby.toLocalDate() + " is before today:"
                        + NOW + " or is not in the correct format!");
            }
            return new Deadline(desc, date);
        } catch (DateTimeParseException e) {
            throw new DukeDateTimeException("The String you entered does not meet the "
                    + "required format of 'yyyy-MM-dd' ");
        }

        
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDateby() + ") You have "
                + (timeLeft() > -1 ? timeLeft() + " days left till its due!" 
                : "this task due since " + timeLeft() * -1 + " days ago!");
    }
    /**
     * Returns a String Representation of the Deadline object class to write to text file.
     * @return the saved task to write to a text file
     */
    @Override
    public String saveTask() {
        return "D" + SEPERATOR + super.saveTask();
    }
}
