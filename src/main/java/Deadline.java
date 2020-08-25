import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate date;
    protected LocalTime time;


    public Deadline(String description, String by) {
        super(description);
        String[] str = by.split(" ", 2);
        this.date = LocalDate.parse(str[0]);
        this.time = LocalTime.parse(str[1]);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " "
                + time.format(DateTimeFormatter.ofPattern("hh:mm a")) + ")";
    }
}
