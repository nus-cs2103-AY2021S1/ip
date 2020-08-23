package main.java;

public class Event extends Task {
    protected String time;

    public Event(String content, String time) {
        super(content);
        this.time = time;
    }

    public Event(boolean status, String content, String time) {
        super(status, content);
        this.time = time;
    }

    public String getTime() { return this.time; }

    @Override
    public String toString() {
        return "[E]" + (super.status ? "[√]" : "[×]") + super.content + "(" + time + ")" + "  <-";
    }
}
