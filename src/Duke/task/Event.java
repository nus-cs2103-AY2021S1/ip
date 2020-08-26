package Duke.task;

public class Event extends Task {
    public String time;

    public Event(String description, String on) {
        super(description);
        this.time = on;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (on: " + time + ")";
    }


}
