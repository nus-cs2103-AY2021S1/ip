//  Events: tasks that start at a specific time and ends at a specific time
//  e.g., team project meeting on 2/10/2019 2-4pm

public class Event extends Task {

    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[E]%1$s (at: %2$s)", super.toString(), this.time);
    }
}