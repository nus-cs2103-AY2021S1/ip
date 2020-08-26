package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    private char type = 'D';
    private LocalDate date;
    private final String BY = "best consumed by: ";
    Deadline(String task, String date) throws DateTimeParseException {
        super(task);
        this.date = LocalDate.parse(date);
    }

    @Override
    public String toString() {
        return String.format("[%c]%s %s%s", type, super.toString(), BY,
                date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }

    @Override
    public String saveToString() {
        return String.format("%c | %s | %s", type, super.saveToString(), date);
    }
}
