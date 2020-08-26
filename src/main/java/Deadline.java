import java.time.LocalDate;

public class Deadline extends Task {

    private LocalDate date;

    public Deadline(String description, String date) {
        super(description);
        this.date = LocalDate.parse(date, Task.INPUT_DATE_TIME_FORMAT);
    }

    public Deadline(String description, String date, boolean isDone) {
        super(description, isDone);
        this.date = LocalDate.parse(date, Task.INPUT_DATE_TIME_FORMAT);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date.format(PRINT_DATE_TIME_FORMAT) + ")";
    }

    @Override
    public String fileText() {
        return "D " + super.fileText() + " | " + this.date.format(INPUT_DATE_TIME_FORMAT);
    }
}