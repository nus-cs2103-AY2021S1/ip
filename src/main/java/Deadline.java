package main.java;

public class Deadline extends Task {
    private String end;

    public Deadline(String s, Boolean b, String e) {
        super(s, b);
        end = e;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String e) {
        end = e;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + (super.isDone() ? "O" : "X") + "] " + super.getName() + " (by: " + end + ")";
    }
}
