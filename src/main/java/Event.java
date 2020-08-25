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

    public String getDateTimeStart() {
      return this.date + ", " + this.time;
    }

    Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    public String getStartDate() {
      return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getDateTimeStart() + ")";
    }
}
