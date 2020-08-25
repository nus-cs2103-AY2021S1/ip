package main.java;

public class Events extends Task {
    private String at;
    private String type = "Events";
    protected Events (String string) {
        super(string.substring(0, string.indexOf("/") - 1), string);
        this.at = string.substring(string.indexOf("/") + 4);
    }
    public String toString() {
        return "[D] " + super.toString() + " (at: " + at + ")";
    }
}
