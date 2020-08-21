package main.java;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime time;
    public Event(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        String datePattern = "dd/MM/yyyy HH:mm";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);
        String date = this.time.format(dateFormatter);
        return "[E]" +"[" + this.getStatusIcon()+"] " + this.description + "(at:" + date +")";
    }
}
