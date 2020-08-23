import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    private LocalDateTime deadline;

    public DeadlineTask(String description, boolean isDone, String deadline) {
        super(description, isDone);
        String[] splitDeadline = deadline.split(" ");
        String inputDeadline = splitDeadline[0] + "T" + splitDeadline[1].substring(0, 2) + ":" + splitDeadline[1].substring(2, 4);
        this.deadline = LocalDateTime.parse(inputDeadline);
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mma")) + ")";
    }
}
