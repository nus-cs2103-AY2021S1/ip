import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task which has a task_info, a deadline date and a time.
 */
public class Deadline extends Task {
    protected LocalDate date;
    protected String time;

    /**
     * Constructs a new Deadline instance.
     *
     * @param task_info Description of the task.
     * @param date Date when the task is due.
     * @param time Time when the task is due.
     */
    public Deadline(String task_info,LocalDate date, String time) {
        super(task_info);
        this.date = date;
        this.time = time;
    }


    /**
     * Overrides the Task's toString method
     * and provides a description of the Deadline instance
     * @return The String that represents the deadline's details.
     */
    @Override
    public String toString() {
        return String.format("[D]" + super.toString() + " by %s %s", date.format(DateTimeFormatter.ofPattern("MMM d yyyy")), time);
    }

}

