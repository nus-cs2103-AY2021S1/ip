package task;

import time.Time;

public class Event extends Task {

    private Time time;

    public Event(String icon, String description, String time) {
        super(icon, description);
        this.time = Time.stringToTime(time);
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + time + ")";
    }
}
