import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public Deadline(String done, String description, LocalDate by) {
        super(description);
        this.by = by;
        this.isDone = done.equals("1");
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " 
                + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
    
    @Override
    public String saveString() {
        return "D" + super.saveString() + " , " + this.by;
    }
}
