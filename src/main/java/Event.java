public class Event extends Task {
    private String at;

    Event(String description, String at) {
        super(description);
        this.at = at;
    }

    Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    public String getStartDate() {
      return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
