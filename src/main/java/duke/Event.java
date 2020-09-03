package duke;

public class Event extends Task {
    // Events: Tasks that start at a specific time and ends at a specific time
    // Example: Team project meeting on 2/10/2019 2-4pm
    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getTaskType() {
        return "Event";
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String toStringInFile() {
        return "E" + super.toStringInFile() + " | " + this.at;
    }
    
}
