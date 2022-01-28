package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Represents a Deadline task that contains
 * description and due details.
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDate date;

    public Deadline(String title, String by) {
        super(title);
        this.by = by;
        if (Pattern.matches("\\d{4}-\\d{2}-\\d{2}", by)) {
            date = LocalDate.parse(by);
        }
    }

    public Deadline(String title, String by, boolean isDone) {
        super(title);
        this.by = by;
        if (Pattern.matches("\\d{4}-\\d{2}-\\d{2}", by)) {
            date = LocalDate.parse(by);
        }
        this.isDone = isDone;
    }

    public Task markAsDone() {
        Task newTask = new Deadline(title, by);
        newTask.isDone = true;
        return newTask;
    }

    public String toString() {
        String status = isDone ? "[✓]" : "[✗]";
        String deadline = date != null
                ? date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                : by;
        return String.format("%s[D] %s (by: %s)", status, title, deadline);
    }

    public String toData() {
        int status = isDone ? 1 : 0;
        return String.format("D | %d | %s | %s", status, title, by);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deadline deadline = (Deadline) o;
        return isDone == deadline.isDone &&
                Objects.equals(title.toLowerCase(), deadline.title.toLowerCase()) &&
                Objects.equals(date, deadline.date);
    }
}