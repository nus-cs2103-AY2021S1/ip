package main.java;

public class Event extends Task{
    private char type = 'E';
    private String time;
    private String at = "bought at: ";
    Event(String task, String time) {
        super(task);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[%c]%s %s%s", type, super.toString(), at, time);
    }

    @Override
    public String saveToString() {
        return String.format("%c | %s | %s", type, super.saveToString(), time);
    }
}
