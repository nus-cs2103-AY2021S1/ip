package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    LocalDateTime deadline;

    public Deadline(String description, String deadline, boolean isDone)
            throws DateTimeParseException {
        super(description, "D", isDone);
        this.deadline = LocalDateTime.parse(deadline,
                DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }

    @Override
    public String toSave() {
        return String.format("%s | %s", super.toSave(),
                this.deadline.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")));
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(),
                this.deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")));
    }

}