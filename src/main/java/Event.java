package main.java;

<<<<<<< HEAD
import java.io.Serializable;

public class Event extends Task implements Serializable {
    private String time;
=======
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate time;
>>>>>>> branch-Level-8

    public Event(String s, Boolean b, LocalDate t) {
        super(s, b);
        time = t;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate t) {
        time = t;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + (super.isDone() ? "O" : "X") + "] " + super.getName() + " (at: "
                + time.format(DateTimeFormatter.ofPattern("MMM dd, yyyy")) + ")";
    }
}
