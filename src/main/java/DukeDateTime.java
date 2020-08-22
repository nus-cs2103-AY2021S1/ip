import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DukeDateTime implements Comparable<DukeDateTime> {

    private LocalDateTime dateTime;
    private boolean containsTime;

    public DukeDateTime(LocalDateTime dateTime, boolean containsTime) {
        this.dateTime = dateTime;
        this.containsTime = containsTime;
    }

    @Override
    public String toString() {
        return containsTime
                ? dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a"))
                : dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    public boolean isSameDate(DukeDateTime other) {
        return dateTime.getYear() == other.dateTime.getYear()
                && dateTime.getMonthValue() == other.dateTime.getMonthValue()
                && dateTime.getDayOfMonth() == other.dateTime.getDayOfMonth();
    }

    @Override
    public int compareTo(DukeDateTime o) {
        if (dateTime.isBefore(o.dateTime)) {
            return -1;
        } else if (dateTime.isAfter(o.dateTime)) {
            return 1;
        } else {
            return 0;
        }
    }
}
