package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Deadline extends Task {

    public LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Deadline deadline = (Deadline) o;
        return Objects.equals(by, deadline.by);
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E, dd/MMM/yyyy HHmm");
        return String.format("[D]%s (by: %s)", super.toString(), by.format(dtf));
    }
}
