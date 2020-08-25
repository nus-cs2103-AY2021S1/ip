package main.java;

public class Event extends Task{

    String taskType = "E";

    public Event(String description) {
        super(description);
    }

    public String formattedDescription() {
        String[] splitBySlash = super.description.split("/", 2);
        if (splitBySlash.length > 1) {
            // description from user
            String[] splitBySpace = splitBySlash[1].split(" ", 2);
            return splitBySlash[0] + "(" + splitBySpace[0] + ": " + splitBySpace[1]
                    + ")";
        } else {
            // description came from System file.
            return splitBySlash[0];
        }
    }

    public String toString() {
        return "[" + taskType + "]" + "[" + super.getStatusIcon()
                + "] " + formattedDescription();
    }
}
