import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    
    private String timing;

    public Deadline (String desc) {
        super(desc.split("deadline ")[1].split(" /by ")[0], "D");
        this.timing = desc.split("deadline ")[1].split(" /by ")[1];
        try {
            String[] split = this.timing.split(" ");
            LocalDate date = LocalDate.parse(split[0]);
            String restOfTime = this.timing.split(split[0])[1];
            this.timing = date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + restOfTime;
        } catch (DateTimeParseException e) {
            // no date
        }
    }

    @Override
    public String toString () {
        return super.toString() + " (by: " + timing + ")";
    }
    
    public String getTiming() {
        return this.timing;
    }
}