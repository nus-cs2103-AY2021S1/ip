package main.java;

public class Event extends Task {
    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public void printDescription() {
        System.out.println("[E][" + getStatusIcon()
                + "] " + description + "(at:" + time + ")");
    }
}
