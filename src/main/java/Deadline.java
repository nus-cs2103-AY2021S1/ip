import java.time.LocalDateTime;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        String[] dateAndTime = by.split(" ");
        String[] date = dateAndTime[0].split("/");
        String time = dateAndTime[1];
        LocalDateTime dateTime = LocalDateTime.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]),
                Integer.parseInt(date[0]), Integer.parseInt(time.substring(0,2)), Integer.parseInt(time.substring(2)));
        this.by = dateTime;
    }

    @Override
    public String toString() {
        String period = by.getHour() >= 12 ? "PM" : "AM";
        String hour = by.getHour() > 12 ? Integer.toString(by.getHour() - 12) : Integer.toString(by.getHour());

        if (by.getMinute() > 0) {
            return "[D]" + super.toString() + " (by: " + by.getDayOfMonth() + " " + by.getMonth() + " " + by.getYear()
                    + " " + hour + "." + by.getMinute() + period + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + by.getDayOfMonth() + " " + by.getMonth() + " " + by.getYear()
                    + " " + hour + period + ")";
        }
    }
}