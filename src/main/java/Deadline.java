import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline with a due date and time.
 */
public class Deadline extends Task {
    private LocalDate dueDate;
    private LocalTime dueTime;

    /**
     * Constructor of the deadline object.
     * @param description The description of the given deadline.
     * @param dueDate The due date of the deadline.
     * @param dueTime The due time of the deadline.
     */
    public Deadline(String description, LocalDate dueDate, LocalTime dueTime) {
        super(description);
        this.dueDate = dueDate;
        this.dueTime = dueTime;
    }

    private Deadline(String description, LocalDate dueDate, LocalTime dueTime, boolean bool) {
        super(description, bool);
        this.dueDate = dueDate;
        this.dueTime = dueTime;
    }

    @Override
    public Deadline changeDesc(String newDesc) {
        return new Deadline(newDesc, this.dueDate, this.dueTime, this.isComplete);
    }

    public Deadline changeTime(LocalTime newTime) {
        return new Deadline(this.description, this.dueDate, newTime, this.isComplete);
    }

    public Deadline changeDate(LocalDate newDate) {
        return new Deadline(this.description, newDate, this.dueTime, this.isComplete);
    }


    /**
     * Returns a Deadline event that is completed.
     * @return Completed Deadline task.
     */
    @Override
    public Deadline markDone() {
        return new Deadline(this.description, this.dueDate, this.dueTime, true);
    }

    @Override
    public String toString() {

        //Format for the date in day, dd MMM yyyy, e.g. Sat, 25 Jan 2020
        DateTimeFormatter myDateFormat = DateTimeFormatter.ofPattern("E, d MMM yyyy");
        //Format for the time in XX:XX aa, e.g. 11:59 pm
        DateTimeFormatter myTimeFormat = DateTimeFormatter.ofPattern("h:mm a");

        String formattedDate = this.dueDate.format(myDateFormat);
        String formattedTime = this.dueTime.format(myTimeFormat);

        if (this.isComplete) {
            return "[D][\u2713] " + this.description + "(by:" + formattedDate + ", " + formattedTime + ")";
        } else {
            return "[D][\u2718] " + this.description + "(by:" + formattedDate + ", " + formattedTime + ")";
        }
    }
}
