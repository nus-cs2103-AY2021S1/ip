public class Event extends Task {
    private String time;
    public Event(boolean isComplete, int index, String instructions, String time) {
        super(isComplete, index, instructions);
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        if (this.isComplete) {
            return "[E][✓] " + this.instructions + " (at: " + this.time + ")";
        } else {
            return "[E][✗] " + this.instructions + " (at: " + this.time + ")";
        }
    }
}
