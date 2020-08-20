package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;

    private final LocalDate date;
    private final LocalTime time;

    public static final String delimiterBy = " /by ";

    public Deadline(String description, String by) {
        super(description);
        this.by = by;

        String[] dateTime = this.by.split(" ");
        this.date = parseDate(dateTime[0]);
        this.time = parseTime(dateTime[1]);
    }

    private LocalDate parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        return LocalDate.parse(dateString, formatter);
    }

    private LocalTime parseTime(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("Hmm");
        return LocalTime.parse(timeString, formatter);
    }

    private String byFormat() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEE, d MMMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mma");
        return String.format("%s, %s", dateFormatter.format(date), timeFormatter.format(time));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byFormat() + ")";
    }
}
