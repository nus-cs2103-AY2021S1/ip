//  Events: tasks that start at a specific time and ends at a specific time
//  e.g., team project meeting on 2/10/2019 2-4pm

public class Event extends Task {

    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public Event(boolean isDone, String description, String time) {
        super(isDone, description);
        this.time = time;
    }

    @Override
    public String fileFormat() {
        return String.format("%1$s|%2$s|%3$s|%4$s", "E", this.isDone ? "0" : "1", this.description, this.time);
    }

    @Override
    public String toString() {
        return String.format("[E]%1$s (at: %2$s)", super.toString(), this.time);
    }
}