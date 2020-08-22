import java.time.LocalDate;

public class Deadline extends Task {

    LocalDate date;

    public Deadline(String taskName, String date) {
        super(taskName);
        this.date = LocalDate.parse(date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + date + ")";
    }
}
