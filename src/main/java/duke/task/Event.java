package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

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

    private String dateSaveFormat() {
        String startDateString = String.format("%sT%s",
                startDate.format(DateTimeFormatter.ofPattern("y-MM-dd")),
                startDate.format(DateTimeFormatter.ofPattern("HH:mm:ss")));

        String endDateString = endDate != null
                ? String.format("%sT%s", endDate.format(DateTimeFormatter.ofPattern("y-MM-dd")),
                    endDate.format(DateTimeFormatter.ofPattern("HH:mm:ss")))
                : "XXXXXXXXXXXXXXXXXXX";

        return startDateString + " to " + endDateString;
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Event) {
            Event e = (Event) o;

            if (e.endDate == null && endDate == null) {
                return e.task.equals(this.task) && e.startDate.equals(startDate);
            } else if (e.endDate != null && endDate != null) {
                return e.task.equals(this.task) && e.startDate.equals(startDate)
                        && e.endDate.equals(endDate);
            } else {
                return false;
            }

        } else {
            return false;
        }
    }

    @Override
    public String saveFormat() {
        return "E" + super.saveFormat() + dateSaveFormat();

    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + dateFormat();
    }
}