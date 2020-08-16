package main.java;

public class Event extends Task {

    private String time;

    public Event(String msg, String time){
        super(msg);
        this.time = time;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
