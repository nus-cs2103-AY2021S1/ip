public class Event extends Task {

    private final String at;

    public Event(String description, String at) {
        super(description, TaskType.EVENT);
        this.at = at;
    }

    @Override
    public String getSavedString() {
        return super.getSavedString() + " | " + at;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + at + ")";
    }
    
}
