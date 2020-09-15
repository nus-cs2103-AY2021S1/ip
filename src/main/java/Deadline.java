import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {

    private LocalDateTime deadline;

    /**
     * Constructor for the class.
     * @param name
     * @param deadline
     */
    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Generate a String of the task.
     * @return a string.
     */
    @Override
    public String printTask() {
        return "[D]" + super.printTask() + " (by: " +
                deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    /**
     * Generate a String to save to the file.
     * @return a String in correct format.
     */
    @Override
    public String toSave() {
        return "D " + super.toSave() + " | " + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }
}
