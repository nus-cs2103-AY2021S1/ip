package main.java;

public class Deadline extends Task{
    private char type = 'D';
    private String time;
    private String by = "best consumed by: ";
    Deadline(String task, String time) {
        super(task);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[%c]%s %s%s", type, super.toString(), by, time);
    }

    @Override
    public String saveToString() {
        return String.format("%c | %s | %s", type, super.saveToString(), time);
    }
}
