import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description , LocalDate by){
        super(description);
        this.by = by;
    }

    public String dateTimeFormat(){
        return this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + this.dateTimeFormat() + ")";
    }

    @Override
    public String write(){
            return "D|" + super.toString() + "|" + this.by;
    }
}
