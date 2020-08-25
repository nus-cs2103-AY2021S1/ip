package main.java;

import java.io.Serializable;

public class Event extends Task implements Serializable {
    private String time;

    public Event(String s, Boolean b, String t) {
        super(s, b);
        time = t;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String t) {
        time = t;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + (super.isDone() ? "O" : "X") + "] " + super.getName() + " (at: " + time + ")";
    }
}
