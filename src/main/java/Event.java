package main.java;

public class Event extends Task{

    String taskType = "E";

    public Event(String description) {
        super(description);
    }

    public String formattedDescription() {
        String[] splitBySlash = super.description.split("/", 2);
        String[] splitBySpace = splitBySlash[1].split(" ", 2);
        return splitBySlash[0] + "(" + splitBySpace[0] + ": " + splitBySpace[1]
                + ")";
    }

    public String toString() {
        return "[" + taskType + "]" + "[" + super.getStatusIcon()
                + "] " + formattedDescription();
    }
}
