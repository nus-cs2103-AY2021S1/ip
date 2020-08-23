package main.java;

public class Deadline extends Task {
    protected String time;

    public Deadline(String content, String time) {
        super(content);
        this.time = time;
    }

    public Deadline(boolean status, String content, String time) {
        super(status, content);
        this.time = time;
    }

    public String getTime() { return this.time; }

    @Override
    public String toString() {
        return "[D]" + (super.status ? "[√]" : "[×]") + super.content + "(" + time + ")" + "  <-";
    }
}
