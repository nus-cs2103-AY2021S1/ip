package duke.tasks;

public class Event extends Task {

    public Event(String description, String at) {
        super(description, at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getNewFormatTime() + ")";
    }
    @Override
    public String toStringFile() {
        return "E" + " | " + (isDone ? "1" : "0") + " | " + this.description + " | " + super.time;
    }
    @Override
    public String getType() {
        return "Event";
    }
}
