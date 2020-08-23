package com.jacob.Duke;


public class Event extends Task {
    public Event(String description) {
        super(description);
        this.type = "E";
    }

    public Event(String description, String dateTime) {
        super(description);
        this.type = "E";
        this.dateTime = dateTime;
    }
}
