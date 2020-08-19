package main.java;

public class Deadline extends Task{
    String taskType = "D";

    public Deadline(String description) {
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
