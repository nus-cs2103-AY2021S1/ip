import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Creates a type of task with a deadline
     *
     * @param description the content of the task
     * @param by          time in yyyy-mm-dd
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by.trim());
    }


    /**
     * Overrides the toString method
     *
     * @return a custom task description
     */
    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.description + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

}