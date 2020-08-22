import java.time.LocalDate;

public class Deadline extends Task {

    LocalDate date;

    public Deadline(String taskName, String date) {
        super(taskName.stripTrailing());
        this.date = LocalDate.parse(date);
    }

    @Override
    public String getInfo() {
        return String.format("D | %d | %s | %s\n", isDone ? 1 : 0, taskName, date.toString());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
