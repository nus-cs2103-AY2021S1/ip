package main.java.duke.tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime date;

    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    @Override
    public String getState() {
        return "D|" + (this.isDone ? "1" : "0") + "|" + this.description + "|" + this.date.toString().replace("T", " ");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm a")) + ")";
    }
}
