package duke.task;

public class Event extends Task {
    protected String at;

    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    public String getParsedTask() {
        return "event " + this.description + " /at " + this.at + System.lineSeparator()
                + this.isDone + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof Event) {
            Event otherEvent = (Event) other;
            return this.description.equals(otherEvent.description)
                    && this.isDone == otherEvent.isDone
                    && this.at.equals(otherEvent.at);
        } else {
            return false;
        }
    }
}
