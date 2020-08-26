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
     * @param item the description of the Deadline
     * @param dateTime the date and time inputted by the user
     * @param dueDate the due date of the Deadline
     * @param input the input given by the user
     * @param completed whether the Deadline is completed
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
     * @return String to be printed to the user
     */
    @Override
    public String getInput() {
        return "[D]" + super.getItem() + "/by " + this.input;
    }

    /**
     * It returns the details of the Deadline
     *
     * @return String to be printed to the user
     */
    @Override
    public String getItem() {
        return "[D]" + super.getItem() + "(by: " + dateTime + ")";
    }
}
