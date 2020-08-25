import java.time.LocalDateTime;

public class Deadline extends Task {
    LocalDateTime deadline;
    public Deadline(String in, LocalDateTime deadline) {
        super(in);
        this.deadline = deadline;
    }
    public String toString() {
        String donez;
        if (done) {
            donez = "✓";
        } else {
            donez = "✗";
        }
        return "[D] [" + donez + "] " + task + " (by: " + dateToString(deadline) + ")";
    }

    public String saveText() {
        return "D | " + super.saveText() + " | " + dateToSave(deadline);
    }
}
