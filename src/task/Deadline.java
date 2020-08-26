package task;

import time.Time;

public class Deadline extends Task {

    private Time deadline;

    public Deadline(String icon, String description, String deadline) {
        super(icon, description);
        this.deadline = Time.stringToTime(deadline);
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + deadline + ")";
    }
}
