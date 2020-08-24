package main.java;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {
    protected LocalDateTime by;

    Deadline(String description, LocalDateTime by){
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy hh.mm a");
        return "[D]" + super.toString() + " (by: " + formatter.format(by) + ")";
    }
}
