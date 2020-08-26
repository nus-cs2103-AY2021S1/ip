package main.java;

public class Event extends Task{

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.type = "E";
    }

    public String getAt(){
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
