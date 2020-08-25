import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate date;
    protected LocalTime time;


    public Deadline(String description, String by) {
        super(description);
        parseDateAndTime(by);
    }

    private void parseDateAndTime(String by) {
        String[] arr = by.split(" ");
        date = LocalDate.parse(arr[0]);
        time = LocalTime.parse(arr[1]);
    }


    private String printDate() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(dateFormatter);
    }

    private String printTime() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        return time.format(timeFormatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + printDate() + " " + printTime() + ")";
    }
}