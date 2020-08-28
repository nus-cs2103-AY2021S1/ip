
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Listing {

    private LocalDate deadLine;

    public Deadline(String s, String deadLine) {
        super(s);
        this.deadLine = LocalDate.parse(deadLine);
    }

    public Deadline(String doneness, String s, String time) {
        super(s);
        checkDoneness(doneness);
        this.deadLine = LocalDate.parse(time);
    }

    public String[] toArray() {
        String[] details = new String[4];
        details[0] = "D";
        if (this.isDone) {
            details[1] = "1";
        } else {
            details[1] = "0";
        }
        details[2] = this.title;
        details[3] = this.deadLine.toString();
        return details;
    }

    @Override
    public String toString() {
        return "[D]" + super.doneness() + this.title + " (by:" +
                deadLine.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
