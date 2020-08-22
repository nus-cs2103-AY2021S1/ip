import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDate deadlineDate;
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.deadlineDate = LocalDate.parse(by);
    }
    
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
        this.deadlineDate = LocalDate.parse(by);
    }

    @Override
    public String getData() {
        return "d/" + super.getData() + "/" + this.by;
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
