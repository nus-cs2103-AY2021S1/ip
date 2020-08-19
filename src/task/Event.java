package task;

public class Event extends Task {

    private String time;

    public Event(String icon, String description, String time) {
        super(icon, description);
        this.time = time;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + time + ")";
    }
}
