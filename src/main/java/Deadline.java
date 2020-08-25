import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Deadline extends Task {

    private final LocalDate dueDate;
    private final LocalTime time;

    //    public Deadline(String name, LocalDate dueDate) throws BlankTaskException {
    //        super(name);
    //        this.dueDate = dueDate;
    //        this.time = null;
    //    }

    public Deadline(String name, LocalDate dueDate, LocalTime time) throws BlankTaskException {
        super(name);
        this.dueDate = dueDate;
        this.time = time;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate.format(DateTimeFormatter.ofPattern("EEE, d MMM yyyy"))
                + (time == null ? "" : ", " + time.format(DateTimeFormatter.ofPattern("h.mm a"))) + ")";
    }

    @Override public String[] attributeList() {
        return new String[] { "D", getName(), dueDate.toString(), time.toString(), String.valueOf(isDone()) };
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Deadline deadline = (Deadline) o;
        return Objects.equals(dueDate, deadline.dueDate) &&
                Objects.equals(time, deadline.time);
    }

    @Override public int hashCode() {
        return Objects.hash(super.hashCode(), dueDate, time);
    }
}
