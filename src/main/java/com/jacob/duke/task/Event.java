package com.jacob.duke.task;


public class Event extends Task {
    public Event(String description) {
        super(description);
        this.type = "E";
    }

    public Event(String description, String dateTime) {
        super(description, dateTime);
        this.type = "E";
    }

}
