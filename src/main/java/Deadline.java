package main.java;

public class Deadline extends Task{
    String taskType = "D";

    public Deadline(String description) {
        super(description);
    }

    public String formattedDescription() {
        String[] splitBySlash = super.description.split("/", 2);
        if (splitBySlash.length > 1) {
            // description came from user input
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
