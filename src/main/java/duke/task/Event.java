package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class Event extends Task {
    protected String at;
    protected LocalDate date;

    public Event(String title, String at) {
        super(title);
        this.at = at;
        if (Pattern.matches("\\d{4}-\\d{2}-\\d{2}", at)) {
            System.out.println("Match!");
            date = LocalDate.parse(at);
        }
    }

    public Event(String title, String at, boolean isDone) {
        super(title);
        this.at = at;
        if (Pattern.matches("\\d{4}-\\d{2}-\\d{2}", at)) {
            System.out.println("Match!");
            date = LocalDate.parse(at);
        }
        this.isDone = isDone;
    }

    public Task markAsDone() {
        Task newTask = new Event(title, at);
        newTask.isDone = true;
        return newTask;
    }

    public String toString() {
        String status = isDone ? "[✓]" : "[✗]";
        String eventTime = date != null
                ? date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                : at;
        return String.format("%s[E] %s (at: %s)", status, title, eventTime);
    }

    public String toData() {
        int status = isDone ? 1 : 0;
        return String.format("D | %d | %s | %s", status, title, at);
    }
}