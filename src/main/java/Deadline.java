import java.time.LocalDateTime;
public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        if(by.getMinute() >= 10) {
            return "[D]" + super.toString() + " (by: " + by.getMonth().toString().substring(0, 3) + " " +
                    by.getDayOfMonth() + " " + by.getYear() + " " +
                    String.format("%d:%d)", by.getHour(), by.getMinute()) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + by.getMonth().toString().substring(0, 3) + " " +
                    by.getDayOfMonth() + " " + by.getYear() + " " + by.getHour() + ":0" + by.getMinute() + ")";
        }

    }
}
