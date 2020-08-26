import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public static Deadline load(String str) {
        String[] arr = str.split("\\|", 4);
        Deadline task = new Deadline(arr[2], arr[3]);
        if (arr[1].equals("true")) {
            task.markAsDone();
        }
        return task;
    }

    @Override
    public String store() {
        return "D|" + super.store() + "|" + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("d MMM uuuu")) + ")";
    }
}