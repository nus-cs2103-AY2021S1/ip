package main.java.TaskList.tasks;

import main.java.TaskList.tasks.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Deadlines extends Task {
    private LocalDate due;
    private DateTimeFormatter inFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter outFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private String type = "Deadlines";
    public Deadlines(String string) throws DateTimeParseException {
        super(string.substring(0, string.indexOf("/") - 1), string);
        this.due = LocalDate.parse(string.substring(string.indexOf("/") + 4), inFormat);
    }
    public String toString() {
        return "[D] " + super.toString() + " (by: " + due.getDayOfWeek() + " " + due.format(outFormat) + ")";
    }
}
