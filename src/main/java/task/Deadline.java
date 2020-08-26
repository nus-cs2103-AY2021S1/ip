package task;

import java.time.LocalDate;

/**
 * Deadline is a task that has a deadline
 *
 * @author (Sruthi)
 */
public class Deadline extends Task {
    private String dateTime;
    private LocalDate dueDate;
    private String input;

    /**
     * Deadline takes in the description of the task, the date and time,
     * due date of the task, the input String by the user and whether the
     * task is completed or not.
     *
     * @param item
     * @param dateTime
     * @param dueDate
     * @param input
     * @param completed
     */
    public Deadline(String item, String dateTime, LocalDate dueDate, String input, boolean completed) {
        super(item, completed);
        this.dateTime = dateTime;
        this.dueDate = dueDate;
        this.input = input;
    }

    /**
     * It returns the input given by the user to the Java Duke Program
     *
     * @return String
     */
    @Override
    public String getInput() {
        return "[D]" + super.getItem() + "/by " + this.input;
    }

    /**
     * It returns the details of the Deadline
     *
     * @return String
     */
    @Override
    public String getItem() {
        return "[D]" + super.getItem() + "(by: " + dateTime + ")";
    }
}
