/**
 * Deadlines is a task that have a deadline which is a string.
 * @author Dominic Siew Zhen Yu
 *
 */
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadlines extends Task{
    private String deadline;
    String TASKINDICATOR = "[D]";

    /**
     * The constructor for the Deadlines class with the userInput (which refers to the name of the task)
     * and the deadline parameter which is the deadline of the deadlines task.
     *
     * @param userInput the name of the deadlines
     * @param deadline the time you have to complete the Deadlines
     */

    public Deadlines(String userInput, String deadline, boolean newInput) {
        super(userInput);

        if (newInput) {
            LocalDate date = LocalDate.parse(deadline);
            this.deadline = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            this.deadline = deadline;
        }

    }

    /**
     * the printName() method prints the name of the deadlines task.
     *
     * @return the string representation of the deadlines
     */

    public String printName() {
        return TASKINDICATOR + " " + super.printName() + " (by: " + this.deadline + " )";
    }
}