package chatbot.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    public Deadline(String description, boolean isDone, LocalDate date) {
        super(description, "D", isDone, date);
    }

    public static Deadline newDeadline(String description, LocalDate date) {
        return new Deadline(description, false, date);
    }

    @Override
    public Deadline markDone() {
        return new Deadline(this.description, true, this.date);
    }

    @Override
    public String toString() {
        String formattedDate = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return super.toString() + " (by: " + formattedDate + ")";
    }
}
