package main.java.com.jacob.duke.task;


public class Deadline extends Task {
    public Deadline(String description) {
        super(description);
        this.type = "D";
    }

    public Deadline(String description, String dateTime) {
        super(description, dateTime);
        this.type = "D";
    }
}
