package Tasks;

import Tasks.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    public String toWrite() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return "[D]" + super.toString() + " /by " + by.format(formatter);
    }
}
