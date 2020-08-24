import java.time.LocalDateTime;

public class Deadline extends Task {

    protected String by;
    protected LocalDateTime dateTime;

    public Deadline(String description, String by) {
        super(description);
        String[] dateAndTime = by.split(" ");
        String[] date = dateAndTime[0].split("/");
        String time = dateAndTime[1];
        LocalDateTime dateTime = LocalDateTime.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]),
                Integer.parseInt(date[0]), Integer.parseInt(time.substring(0,2)), Integer.parseInt(time.substring(2)));

        this.by = by;
        this.dateTime = dateTime;
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        String period = dateTime.getHour() >= 12 ? "PM" : "AM";
        String hour = dateTime.getHour() > 12 ? Integer.toString(dateTime.getHour() - 12)
                : Integer.toString(dateTime.getHour());

        if (dateTime.getMinute() > 0) {
            return "[D]" + super.toString() + " (by: " + dateTime.getDayOfMonth() + " " + dateTime.getMonth() + " "
                    + dateTime.getYear() + " " + hour + "." + dateTime.getMinute() + period + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + dateTime.getDayOfMonth() + " " + dateTime.getMonth() + " "
                    + dateTime.getYear() + " " + hour + period + ")";
        }
    }
}