package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Event extends Task {

    public LocalDateTime at;
    public LocalDateTime end;

    public Event(String description, LocalDateTime at, LocalDateTime end) {
        super(description);
        this.at = at;
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Event event = (Event) o;
        return Objects.equals(at, event.at) && Objects.equals(end, event.end);
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E, dd/MMM/yyyy HHmm");
        return String.format(
                "[E]%s (at: %s till %s)", super.toString(), at.format(dtf), end.format(dtf));
    }
}
