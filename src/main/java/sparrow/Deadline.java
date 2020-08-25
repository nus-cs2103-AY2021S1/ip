package sparrow;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate dueDate;

    public Deadline(String description, LocalDate dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return String.format("[D]%s (by: %s)", super.toString(), this.dueDate.format(formatter));
    }
}
