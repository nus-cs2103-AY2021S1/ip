import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
}
