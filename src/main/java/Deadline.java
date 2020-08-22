package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {
    LocalDate time;

    Deadline(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        String formattedTime = time.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        return "[D]" + super.toString() + " (by: " + formattedTime + ")";
    }
}
