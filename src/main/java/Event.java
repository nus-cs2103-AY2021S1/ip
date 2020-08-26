package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    private char type = 'E';
    private LocalDate date;
    private final String AT = "bought at: ";
    Event(String task, String date) throws DateTimeParseException {
        super(task);
        this.date = LocalDate.parse(date);
    }

    @Override
    public String toString() {
        return String.format("[%c]%s %s%s", type, super.toString(), AT,
                date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
