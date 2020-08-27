import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    public String type;

    public Deadline(String desc, boolean isDone, LocalDate time) {
        super(desc, isDone, time);
        type = "D";
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
//        String formattedDate = this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return super.toString();
    }
}
