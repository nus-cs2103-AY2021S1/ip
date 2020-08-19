package main.java;

public class Events extends Task {
    String at;
    protected Events (String string) {
        super(string.substring(0, string.indexOf("/") - 1));
        this.at = string.substring(string.indexOf("/") + 4);
    }
    public String toString() {
        return "[D] " + super.toString() + " (at: " + at + ")";
    }
}
