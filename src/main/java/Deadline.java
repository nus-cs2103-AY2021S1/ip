import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task{
    protected String by;

    //Constructors
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public Deadline markAsDone() {
        return new Deadline(this.description, true, this.by);
    }

    @Override
    public String stringify() {
        String number = isDone ? "1" : "0";
        return "D | " + number + " | " + super.description + " | " + this.by;
    }
    
    @Override
    public String toString() {
        return "[D]" + "[" + super.getStatusIcon() + "] " + super.toString() + " (by: " + by + ")";
    }
}
