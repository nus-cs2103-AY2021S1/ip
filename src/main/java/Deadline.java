package main.java;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String datePattern = "dd/MM/yyyy HH:mm";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);
        String date = this.deadline.format(dateFormatter);
        return "[D]" +"[" + this.getStatusIcon()+"] " + this.description + "(by:" + date +")";
    }
}
