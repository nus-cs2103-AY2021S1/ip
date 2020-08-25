package main.java.com.jacob.duke.task;


public class Event extends Task {
    /**
     * Constructor for basic Description
     * @param description Task Description
     */
    public Event(String description) {
        super(description);
        this.type = "E";
    }

    /**
     * Constructor to parse date time format
     * @param description Task Description
     * @param dateTime Date Time format
     */
    public Event(String description, String dateTime) {
        super(description, dateTime);
        this.type = "E";
    }

}
