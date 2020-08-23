package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate time;

    public Deadline(String content, String time) {
        super(content);
        this.time = LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        return "[D]" + (super.status ? "[√]" : "[×]") + super.content + "(" + time.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")" + "  <-";
    }
}
