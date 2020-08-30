import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate date;
    protected String time;

    public Deadline(String task_info,LocalDate date, String time) {
        super(task_info);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[D]" + super.toString() + " by %s %s", date.format(DateTimeFormatter.ofPattern("MMM d yyyy")), time);
    }

}

