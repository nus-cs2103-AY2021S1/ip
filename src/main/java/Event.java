package main.java;

public class Event extends Task {
    protected String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String getState() {
        return "E|" + (this.isDone ? "1" : "0") + "|" + this.description + "|" + this.date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + date + ")";
    }
}
