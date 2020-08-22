package main.java;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime time;
    public Event(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }
    public Event(String description, boolean isDone, LocalDateTime time) {
        super(description, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        String datePattern = "dd/MM/yyyy HH:mm";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);
        String date = this.time.format(dateFormatter);
        return "[E]" + "[" + this.getStatusIcon() + "] " + this.description + "(at:" + date + ")";
    }

    @Override
    public String writeToFile() {
        String result = "D # ";
        if(isDone) {
            result += "1 # ";
        } else {
            result += "0 # ";
        }
        result += description;
        String datePattern = "dd/MM/yyyy HH:mm";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);
        result+=" # " + time.format(dateFormatter);
        return result;

    }
}
