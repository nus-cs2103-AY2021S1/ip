package main.java;

public class Event extends Task{
    private char type = 'E';
    private String time;
    Event(String task, String time) {
        super(task);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[%c]%s %s", type, super.toString(), time);
    }
}
