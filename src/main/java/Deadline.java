import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String deadlineString;
    private LocalDate deadline = null;

    public Deadline(String taskName, String deadlineString) {
        super(taskName);
        try {
            deadline = LocalDate.parse(deadlineString);
            this.deadlineString = deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        } catch (DateTimeException e) {
            this.deadlineString = deadlineString;
        }
    }

    @Override
    public String getStorageFormat() {
        return "D | " + super.getStorageFormat() + " | " + deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineString + ")";
    }

    public static Deadline makeTaskFromInput(String taskName, String deadline) throws DukeException {
        if (taskName.isBlank()) {
            throw DukeException.badDeadlineTask();
        } else if (deadline.isBlank()) {
            throw DukeException.badDeadlineDate();
        }

        return new Deadline(taskName, deadline);
    }
}
