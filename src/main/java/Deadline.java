import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate deadline;

    Deadline(String s, String deadline) {
        super(s);
        this.deadline = LocalDate.parse(deadline);
    }

    Deadline(String name, boolean completed, String deadline) {
        super(name, completed);
        this.deadline = LocalDate.parse(deadline);
    }

    private Deadline(String name, boolean completed, LocalDate deadline) {
        super(name, completed);
        this.deadline = deadline;
    }

    @Override
    public String getType() {
        return "deadline";
    }

    @Override
    public String getTime() {
        return deadline.toString();
    }

    @Override
    public Deadline completeTask() {
        return new Deadline(this.name, true, this.deadline);
    }

    @Override
    public String toString() {
        return "[deadline]" + super.toString()
                + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
