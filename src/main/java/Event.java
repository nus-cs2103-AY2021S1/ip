package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate time;

    public Event(String content, String time) {
        super(content);
        this.time = LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public Event(boolean status, String content, String time) {
        super(status, content);
        this.time = LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getTime() { return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); }

    @Override
    public String toString() {
        return "[E]" + (super.status ? "[√] " : "[×] ") + super.content + "(on " + time.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")" + "  <-";
    }
}
