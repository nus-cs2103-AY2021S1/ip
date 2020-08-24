import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private Event (String task, LocalDateTime startDate, LocalDateTime endDate, boolean isDone) {
        super(task, isDone);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Event(String task, LocalDateTime startDate, LocalDateTime endDate) {
        super(task);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Event(String task, LocalDateTime date) {
        super(task);
        this.startDate = date;
        this.endDate = null;
    }

    private String dateFormat() {
        if (endDate != null) {
            return String.format(" (at: %s to %s)",
                    startDate.format(DateTimeFormatter.ofPattern("dd MMM y, h:mm a")),
                    startDate.toLocalDate().equals(endDate.toLocalDate())
                            ? endDate.format(DateTimeFormatter.ofPattern("h:mm a"))
                            : endDate.format(DateTimeFormatter.ofPattern("dd MMM y, h:mm a")));
        } else {
            return String.format(" (at: %s)",
                    startDate.format(DateTimeFormatter.ofPattern("dd MMM y, h:mm a")));
        }
    }

    @Override
    public Event markDone() {
        return new Event(task, startDate, endDate, true);
    }

    @Override
    public LocalDate getDate() {
        return startDate.toLocalDate();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + dateFormat();
    }
}