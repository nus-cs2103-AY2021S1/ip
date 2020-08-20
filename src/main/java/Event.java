package main.java;

public class Event extends Task {
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

}
