package main.java;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate date;
    private LocalTime time;

    public Deadline(String msg, String date, String time){
        super(msg);
        this.date = LocalDate.parse(date);

        if (!time.equals("")) {
            this.time = LocalTime.parse(time);
        }
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +  date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                ((time != null) ? " " + time.format(DateTimeFormatter.ofPattern("HH:mm")) : "") + ")";
    }
}
