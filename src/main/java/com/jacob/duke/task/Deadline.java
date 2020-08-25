package main.java.com.jacob.duke.task;

public class Deadline extends Task {
    /**
     * Constructor for basic Description
     * @param description Task Description
     */
    public Deadline(String description) {
        super(description);
        this.type = "D";
    }
    /**
     * Constructor to parse date time format
     * @param description Task Description
     * @param dateTime Date Time format
     */
    public Deadline(String description, String dateTime) {
        super(description, dateTime);
        this.type = "D";
    }
}
