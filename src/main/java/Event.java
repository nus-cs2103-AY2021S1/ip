import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Event extends Task {
    private String date;
    private String time;

    Event(String description, String date, String time) {
        super(description);

        try {
            Date d = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            this.date = new SimpleDateFormat("MMM d yyyy").format(d);

            LocalTime t = LocalTime.parse(time.substring(0, 2) + ":" + time.substring(2));
            this.time = DateTimeFormatter.ofPattern("h.mma").format(t);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    Event(String description, boolean isDone, String dateTime) {
        super(description, isDone);
        String[] split = dateTime.split(",");

        this.date = split[0].trim();
        this.time = split[1].trim();
    }

    public String getStartDate() {
      return this.date + ", " + this.time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getStartDate() + ")";
    }
}
