import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Listing{
    public Deadline(String s, String deadLine) {
        super(s);
        this.deadLine = LocalDate.parse(deadLine);
    }
    LocalDate deadLine;


    @Override
    public String toString() {
        return "[D]" + super.doneness() + this.title + " (by:" +
                deadLine.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
