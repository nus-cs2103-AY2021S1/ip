package duke.Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Deadline class is a children object of the Task class
 * with the additional abilities to
 * returns due date of the Deadline
 * returns original command of the Deadline
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Creates a Deadline Object
     * @param description
     * @param by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Creates a Deadline Object
     * @param description
     * @param by
     * @param isDone
     */
    public Deadline(String description, String by, boolean isDone){
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns the due date of the Deadline
     * @return date
     */
    public String getDate(){

        return this.by;
    }

    /**
     * Returns the original command used to create the Deadline
     * @return original command
     */
    @Override
    public String getOriginal() {
        return "deadline " + getTask() + " /by " + getDate();
    }

    /**
     * Returns the due date in the form of dd MMM yyyy HHmm
     * @return due date of the Deadline
     */
    public String getDeadlineDate() {
        String[] dateList = this.by.split(" ",2);
        LocalDate deadlineDate = LocalDate.parse(dateList[0]);
        DateTimeFormatter FormatDate = DateTimeFormatter.ofPattern("dd MMM yyyy");

        if(dateList.length > 1){
            String deadlineTime = dateList[1];
            return deadlineDate.format(FormatDate) + " " + deadlineTime;
        }
        else{
            return deadlineDate.format(FormatDate);
        }

    }

    /**
     * Returns the Deadline with StatusIcon
     * @return Deadline with StatusIcon
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDeadlineDate() + ")";
    }
}
