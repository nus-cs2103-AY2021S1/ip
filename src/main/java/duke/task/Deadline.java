package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.error.DeadlineDateParseException;

/**
 * Class that represents a deadline item in the taskList.
 *
 * @author Roger Lim
 */

public class Deadline extends Task {
    private LocalDate time;


    /**
     * Create an instance of Deadline with description and time specified.
     * Done is marks as false.
     * @param description The description for the deadline.
     * @param time The time of the deadline.
     * @throws DeadlineDateParseException
     */
    public Deadline(String description, String time) throws DeadlineDateParseException {
        super(description);
        try {
            this.time = LocalDate.parse(time);;
        } catch (DateTimeParseException e) {
            throw new DeadlineDateParseException(time);
        }
    }

    /**
     * Create an instance of Deadline with description, time and isDone specified.
     * @param description The description for the deadline.
     * @param isDone Boolean value representing if the deadline task is done.
     * @param time The time of the deadline.
     */
    public Deadline(String description, boolean isDone, String time) {
        super(description, isDone);
        this.time = LocalDate.parse(time);
    }

    /**
     * formats the Date time.
     * @return Formatted date time.
     */
    String printTime() {
        return time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Shows the status and type of task.
     * @return [D] and tick or cross depending in the status.
     */
    @Override
    public String getStatusIcon() {
        return String.format("[D]%s", super.getStatusIcon(), printTime());
    }

    /**
     * Returns a string that can be stored in data.txt.
     * @return String that has the details of this object.
     */
    @Override
    public String writeToFile() {
        int done = isDone ? 1 : 0;
        return String.format("D//%d//%s//%s\n", done, this.description, this.time);
    }

    /**
     * Creates the string that is shown to the user.
     * @return String.
     */
    @Override
    public String getOutput() {
        return String.format("%s %s(By: %s)", getStatusIcon(), this.description, printTime());
    }

    public LocalDate getTime() {
        return time;
    }

}
