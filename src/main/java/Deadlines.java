package main.java;

public class Deadlines extends Task {
    String due;
    protected Deadlines (String string) {
        super(string.substring(0, string.indexOf("/") - 1));
        this.due = string.substring(string.indexOf("/") + 4);
    }
    public String toString() {
        return "[D] " + super.toString() + " (by: " + due + ")";
    }
}
