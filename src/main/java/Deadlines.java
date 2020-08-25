package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Deadlines extends Task {
    private LocalDate due;
    private DateTimeFormatter inFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter outFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private String type = "Deadlines";
    protected Deadlines (String string) {
        super(string.substring(0, string.indexOf("/") - 1), string);
        this.due = LocalDate.parse(string.substring(string.indexOf("/") + 4), inFormat);
    }
    public String toString() {
        return "[D] " + super.toString() + " (by: " + due.getDayOfWeek() + " " + due.format(outFormat) + ")";
    }
}
