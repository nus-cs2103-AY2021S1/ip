import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task {

    private String at;

    public Event(String description, String at) throws DateException {
        super(description);
        this.at = processDate(at);
    }

    public static String processDate(String at) throws DateException {
        try {
            String[] times = at.split(" ");
            String d = "";
            String t = "";
            if (times.length == 1) {
                LocalDate date = LocalDate.parse(times[0]);
                d = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                return d;
            } else {
                LocalDate date = LocalDate.parse(times[0]);
                d = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

                double time = Double.parseDouble(times[1]);
                if (time >= 1300) {
                    time -= 1200;
                }
                int hour = (int) (time / 100);
                if(hour == 24 || hour == 0) {
                    t += "12.";
                } else {
                    t += hour + ".";
                }
                int minute = (int) (time % 100);
                if (minute < 10) {
                    t += "0" + minute;
                } else {
                    t += minute;
                }
                if ((Double.parseDouble(times[1]) >= 1200) &&
                        (Double.parseDouble(times[1]) < 2400)) {
                    t += "pm";
                } else {
                    t += "am";
                }
                return d + " " + t;
            }
        } catch (Exception e) {
            throw new DateException("Sorry! I don't understand the date/time. Please specify the date/time " +
                    "in YYYY-MM-DD or YYYY-MM-DD HHMM format.");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
