import java.time.LocalDate;
import java.time.LocalTime;

// partial solution copied from iP
public class Event extends Task {

    protected LocalDate date;
    protected LocalTime time;

    public Event(String description, String at, boolean isDone) {

        super(description.trim(), isDone);

        String[] dateAndTime = at.trim().split(" ");
        String[] date = dateAndTime[0].split("/");

        String time;

        if (dateAndTime.length == 1) {
            time = "2359";
        } else {
            time = String.format("%04d", Integer.parseInt(dateAndTime[1]));
        }

        String day = date[0];
        String month = date[1];
        String year = date[2];

        this.date = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));

        String hour = time.substring(0, 2);
        String minute = time.substring(2, 4);

        if (Integer.parseInt(hour) <= 23 && Integer.parseInt(minute) <= 59) {
            this.time = LocalTime.of(Integer.parseInt(hour), Integer.parseInt(minute));
        } else {
            this.time = LocalTime.of(23, 59);
        }
    }

    @Override
    public String toString() {
        String formattedTime = String.format("%02d", time.getHour()) + String.format("%02d", time.getMinute());
        return "  [E]" + super.toString() + " (At: " + date.getDayOfMonth() + " "
                + date.getMonth().toString().toLowerCase() + " " + date.getYear() + (time == null ? ")" : " "
                + formattedTime + " hrs)");
    }
}
