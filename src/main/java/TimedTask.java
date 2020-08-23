import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TimedTask extends Task {
    private final LocalDate date;
    private final String time;
    private final String tag;

    public TimedTask (String description, String time, String tag) {
        super(description);
        String dt = "";
        if (time.indexOf(' ') > 0) {
            dt = time.substring(0, time.indexOf(' '));
            String tm = time.substring(time.indexOf(' ') + 1);
            this.time = parseTime(tm);
        } else {
            dt = time;
            this.time = "";
        }
        date = parseDate(dt);
        this.tag = tag;
    }

    public TimedTask (String description, String time, LocalDate date, String tag) {
        super(description);
        this.time = time;
        this.date = date;
        this.tag = tag;
    }

    public String getTime() {
        return this.time;
    }

    public LocalDate getDate() {
        return this.date;
    }

    private LocalDate parseDate (String dt) {
        LocalDate parsedDate;
        String day = dt.substring(0, dt.indexOf('/'));
        String month = dt.substring(dt.indexOf('/') + 1, dt.lastIndexOf('/'));
        String year = dt.substring(dt.lastIndexOf('/') + 1);
        parsedDate = LocalDate.parse(year + "-" + month + "-" + day);
        return parsedDate;
    }

    private String parseTime(String time) {
        int tm = Integer.parseInt(time);
        String hr = "";
        String ampm = "";
        String min = "";
        if (tm / 100 > 12) {
            hr = String.format("%d:", (tm / 100) % 12);
            ampm = " pm";
        } else {
            hr = String.format("%d:", (tm / 100));
            ampm = " am";
        }
        if (tm % 100 == 0) {
            min = "00";
        } else {
            min = String.valueOf(tm % 100);
            if (tm % 100 < 10) {
                min = "0" + min;
            }
        }
        if (time.equals("1200")) {
            return "12:00 pm";
        }
        return hr + min + ampm;
    }

    @Override
    public String getDescription() {
        String message = this.tag + super.getDescription() + " " + this.getSchedule();
        return message;
    }

    public String getSchedule() {
        String timeTag = "";
        if (tag == "[D]") {
            timeTag = "(by: ";
        } else {
            timeTag = "(at: ";
        }
        return timeTag + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy "))
                + time + ")";
    }

}
