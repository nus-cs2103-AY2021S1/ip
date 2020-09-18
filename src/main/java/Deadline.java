/**
 * A tasks that has a deadline
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task{

    protected LocalDate by;

    /**
     * Deadline constructor
     * @param description details of the task
     * @param by the deadline of the task
     */
    public Deadline(String description , LocalDate by){
        super(description);
        this.by = by;
    }

    /**
     * method to change deadline to a particular format
     * @return time in "MMM dd yyyy" format
     */
    public String dateTimeFormat(){
        return this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * task to be written as string
     * @return string to be presented in a list
     */
    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + this.dateTimeFormat() + ")";
    }

    /**
     * task to be written in file
     * @return string to be written in a file
     */
    @Override
    public String write(){
            return "D|" + this.getStatusIcon() + "|" + this.description + "|" + this.by;
    }
}
