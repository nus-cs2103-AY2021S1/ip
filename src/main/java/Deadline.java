package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate time;

    public Deadline(String content, String time) {
        super(content);
        this.time = LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public Deadline(boolean status, String content, String time) {
        super(status, content);
        this.time = LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getTime() { return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); }

    @Override
    public String toString() {
        return "[D]" + (super.status ? "[√]" : "[×]") + super.content + "(by " + time.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")" + "  <-";
    }
}
