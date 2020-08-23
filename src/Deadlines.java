import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    protected LocalDateTime deadline;

    public Deadlines(String task, LocalDateTime deadline) {
        super(task);
        this.deadline = deadline;
    }

    public Deadlines(String task, String deadline, boolean isDone) {
        super(task,isDone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(deadline, formatter);
        this.deadline = dateTime;
    }

    @Override
    public String toString() {
        String dateTime = deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
        return "[D]" + super.toString() + " (by: " + dateTime + ")";
    }

}
