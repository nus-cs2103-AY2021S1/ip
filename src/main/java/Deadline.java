package main.java;

public class Deadline extends Task{
    private char type = 'D';
    private String time;
    Deadline(String task, String time) {
        super(task);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[%c]%s %s", type, super.toString(), time);
    }
}
