import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(boolean isDone,String description,LocalDateTime by) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    public String toFileStringFormat() {
        return String.format("D / %d / %s / %s",isDone ? 1 : 0, this.desciption,this.by);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}
