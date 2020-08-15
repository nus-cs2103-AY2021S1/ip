package main.java;

public class Event extends Task{
    protected String description;
    protected String time;

    Event(String description, String time){
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
