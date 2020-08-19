package src.main.java;

public class Event extends Task {
    protected String time;

    public Event(String item, String sign, String time) {
        super(item, sign);
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }
}
