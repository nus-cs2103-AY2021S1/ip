<<<<<<< HEAD
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Listing{
=======
public class Deadline extends Listing {
    String deadLine;

>>>>>>> branch-Level-7
    public Deadline(String s, String deadLine) {
        super(s);
        this.deadLine = LocalDate.parse(deadLine);
    }
<<<<<<< HEAD
    LocalDate deadLine;

=======

    public Deadline(String doneness, String s, String time) {
        super(s);
        checkDoneness(doneness);
        this.deadLine = time;
    }

    public String[] toArray() {
        String[] details = new String[4];
        details[0] = "T";
        if (this.done) {
            details[1] = "1";
        } else {
            details[1] = "0";
        }
        details[2] = this.title;
        details[3] = this.deadLine;
        return details;
    }
>>>>>>> branch-Level-7

    @Override
    public String toString() {
        return "[D]" + super.doneness() + this.title + " (by:" +
                deadLine.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
